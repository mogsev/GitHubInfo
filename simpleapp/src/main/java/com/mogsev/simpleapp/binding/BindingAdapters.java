package com.mogsev.simpleapp.binding;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public final class BindingAdapters {
    private static final String TAG = BindingAdapters.class.getSimpleName();

    @BindingAdapter("app:onClick")
    public static void bindOnClick(final View view, final Runnable runnable) {
        view.setOnClickListener(v -> runnable.run());
    }

}
