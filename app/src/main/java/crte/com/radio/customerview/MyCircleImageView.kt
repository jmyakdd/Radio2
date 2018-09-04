package crte.com.radio.customerview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import crte.com.radio.R

class MyCircleImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    constructor(context: Context?) : this(context, null)

    var paint: Paint
    lateinit var imageBitmap: Bitmap
    var mWidth = 0
    var mRadius = 0f

    init {
        paint = Paint()
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        setBitmapShader()
        canvas?.drawCircle(mRadius, mRadius, mRadius, paint)
        super.onDraw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = Math.min(measuredWidth, measuredHeight)
        mRadius = mWidth * 1.0f / 2
        setMeasuredDimension(mWidth, mWidth)
    }

    fun setBitmapShader() {
        imageBitmap = BitmapFactory.decodeResource(resources, R.mipmap.bg)
        var mBitWidth = Math.min(imageBitmap.width, imageBitmap.height)
        var b = Bitmap.createBitmap(imageBitmap, imageBitmap.width / 2 - mWidth / 2, imageBitmap.height / 2 - mWidth / 2, imageBitmap.width / 2 + mWidth / 2, imageBitmap.height / 2 + mWidth / 2)
        var mBitmapShader = BitmapShader(b, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        var scale = mWidth * 1.0f / mBitWidth
        var mMatrix = Matrix()
        matrix.setScale(scale, scale)
        mBitmapShader.setLocalMatrix(mMatrix)
        paint.setShader(mBitmapShader)
    }
}