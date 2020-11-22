package com.example.background.workers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import com.example.background.R;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class BlurWorker extends Worker {
    public BlurWorker(
            @NonNull Context appContext,
            @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }
    private static final String TAG = BlurWorker.class.getSimpleName();
    @NonNull
    @Override
    public Result doWork() {
        Context applicationContext = getApplicationContext();
        try {
            Bitmap picture = BitmapFactory.decodeResource(
                    applicationContext.getResources(),
                    R.drawable.test);
            // Blur the bitmap
            Bitmap output = WorkerUtils.blurBitmap(picture, applicationContext);

            // Write bitmap to a temp file
            Uri outputUri = WorkerUtils.writeBitmapToFile(applicationContext, output);
            WorkerUtils.makeStatusNotification("Output is "
                    + outputUri.toString(), applicationContext);

            // If there were no errors, return SUCCESS
            return Result.success();
        }
        catch (Throwable throwable) {
            // Technically WorkManager will return Result.failure()
            // but it's best to be explicit about it.
            // Thus if there were errors, we're return FAILURE

            Log.e(TAG, "Error applying blur", throwable);
            return Result.failure();
        }
    }
}

