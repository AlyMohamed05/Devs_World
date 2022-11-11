package com.silverbullet.devsworld.core.presentation.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract
import com.yalantis.ucrop.UCrop

class CropActivityResultContract(
    private val destinationUri: Uri
) : ActivityResultContract<Uri, Uri?>() {

    //TODO: Add a way to configure crop options

    override fun createIntent(context: Context, input: Uri): Intent {
        return UCrop
            .of(input, destinationUri)
            .withAspectRatio(16f, 9f)
            .getIntent(context)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        return UCrop.getOutput(intent ?: return null)
    }
}