package com.silverbullet.devsworld.core.presentation.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract
import com.yalantis.ucrop.UCrop

class CropActivityResultContract(
    private val destinationUri: Uri,
    private val configure: UCrop.() -> UCrop
) : ActivityResultContract<Uri, Uri?>() {

    override fun createIntent(context: Context, input: Uri): Intent {
        return UCrop
            .of(input, destinationUri)
            .configure()
            .getIntent(context)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        return UCrop.getOutput(intent ?: return null)
    }
}