package io.github.tonnyl.spark

import android.graphics.drawable.AnimationDrawable
import android.view.View

/**
 * Created by lizhaotailang on 26/08/2017.
 *
 * Create a background animation like instagram.
 */
class Spark private constructor(private val mDuration: Int,
                                private val mView: View?,
                                private val mAnimList: Int) {

    companion object {
        @JvmField
        val ANIM_GREEN_PURPLE = R.drawable.anim_green_purple
        @JvmField
        val ANIM_BLUE_PURPLE = R.drawable.anim_blue_purple
        @JvmField
        val ANIM_RED_PURPLE = R.drawable.anim_red_purple
    }

    private constructor(builder: Builder) :
            this(builder.duration, builder.view, builder.animList)

    fun startAnimation() {

        mView?.let {
            it.setBackgroundResource(mAnimList)
            val background = it.background as AnimationDrawable

            background.setEnterFadeDuration(mDuration)
            background.setExitFadeDuration(mDuration)

            it.background = background
            it.post {
                background.start()
            }
        } ?: run {
            throw IllegalStateException("view must be initialized")
        }
    }

    class Builder {

        var duration: Int = 4000
            private set
        var view: View? = null
            private set
        var animList: Int = ANIM_GREEN_PURPLE
            private set

        fun setDuration(duration: Int): Builder {
            this.duration = duration
            return this
        }

        fun setView(view: View): Builder {
            this.view = view
            return this
        }

        fun setAnimList(animList: Int): Builder {
            this.animList = animList
            return this
        }

        fun build() = Spark(this)
    }

}