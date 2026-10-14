package com.example.domain.pdf

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

object PdfPageRenderer {
    private const val TAG = "PdfPageRenderer"

    data class PdfMeta(
        val totalPages: Int,
        val firstPageCacheUri: String?
    )

    suspend fun getPdfMetadata(context: Context, pdfUri: Uri): PdfMeta = withContext(Dispatchers.IO) {
        var pfd: ParcelFileDescriptor? = null
        var renderer: PdfRenderer? = null
        try {
            pfd = context.contentResolver.openFileDescriptor(pdfUri, "r")
            if (pfd != null) {
                renderer = PdfRenderer(pfd)
                val totalPages = renderer.pageCount
                var cacheUri: String? = null
                if (totalPages > 0) {
                    val page = renderer.openPage(0)
                    val width = (page.width * 1.0f).toInt().coerceAtLeast(100)
                    val height = (page.height * 1.0f).toInt().coerceAtLeast(100)
                    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                    val canvas = Canvas(bitmap)
                    canvas.drawColor(Color.WHITE)
                    page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                    page.close()

                    val cacheFile = File(context.cacheDir, "pdf_preview_${System.currentTimeMillis()}.jpg")
                    FileOutputStream(cacheFile).use { out ->
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, out)
                    }
                    cacheUri = Uri.fromFile(cacheFile).toString()
                }
                return@withContext PdfMeta(totalPages, cacheUri)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error getting PDF metadata", e)
        } finally {
            renderer?.close()
            pfd?.close()
        }
        PdfMeta(0, null)
    }

    suspend fun renderPageToBitmap(
        context: Context,
        pdfUri: Uri,
        pageIndex: Int,
        renderScale: Float = 1.5f
    ): Bitmap? = withContext(Dispatchers.IO) {
        var pfd: ParcelFileDescriptor? = null
        var renderer: PdfRenderer? = null
        var page: PdfRenderer.Page? = null
        try {
            pfd = context.contentResolver.openFileDescriptor(pdfUri, "r") ?: return@withContext null
            renderer = PdfRenderer(pfd)
            if (pageIndex in 0 until renderer.pageCount) {
                page = renderer.openPage(pageIndex)
                val width = (page.width * renderScale).toInt().coerceAtLeast(100)
                val height = (page.height * renderScale).toInt().coerceAtLeast(100)
                val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitmap)
                canvas.drawColor(Color.WHITE)
                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                return@withContext bitmap
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error rendering PDF page $pageIndex", e)
        } finally {
            page?.close()
            renderer?.close()
            pfd?.close()
        }
        null
    }

    fun clearPdfCache(context: Context) {
        try {
            context.cacheDir.listFiles()?.forEach { file ->
                if (file.name.startsWith("pdf_")) {
                    file.delete()
                }
            }
        } catch (e: Exception) {
            Log.w(TAG, "Failed to clear PDF cache", e)
        }
    }
}
