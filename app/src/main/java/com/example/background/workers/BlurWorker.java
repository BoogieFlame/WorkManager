package com.example.background.workers;

import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;

public class BlurWorker extends Woker {
    public BlurWorker(
            @NonNull Context appContext,
            @NonNull WorkerParameters workerParams) {
              super (appContext, workerParams);
    }
}
