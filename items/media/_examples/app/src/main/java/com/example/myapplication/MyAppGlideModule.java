package com.example.myapplication;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * This class is a GlideModule implementation for the application.
 * GlideModule is a way to make Glide's configuration more flexible and allow applications to integrate with Glide's API.
 * This class is annotated with @GlideModule, which is used by Glide's annotation processor to discover implementations.
 * The MyAppGlideModule class extends AppGlideModule, which provides default implementations for all of AppGlideModule's methods.
 * This allows the application to only override the methods it needs to.
 * In this case, the class is empty, meaning it uses the default implementations for all methods.
 *
 * @author Yichi Zhang u7748799
 */
@GlideModule
public class MyAppGlideModule extends AppGlideModule {
    // Empty implementation
}