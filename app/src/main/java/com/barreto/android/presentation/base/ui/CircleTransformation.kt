package com.barreto.android.presentation.base.ui

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader
import com.squareup.picasso.Transformation

class CircleTransform : Transformation {

    override fun transform(source: Bitmap): Bitmap {
        val size = Math.min(source.width, source.height)

        val x = (source.width - size) / 2
        val y = (source.height - size) / 2

        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap != source) {
            source.recycle()
        }

        val bitmap = Bitmap.createBitmap(size, size, source.config)

        val r = size / 2f
        Canvas(bitmap).apply {
            drawCircle(
                    r,
                    r,
                    r,
                    Paint().apply {
                        shader = BitmapShader(
                                squaredBitmap,
                                Shader.TileMode.CLAMP,
                                Shader.TileMode.CLAMP
                        )
                        isAntiAlias = true
                    }
            )
        }

        squaredBitmap.recycle()
        return bitmap
    }

    override fun key(): String {
        return "circle"
    }
}