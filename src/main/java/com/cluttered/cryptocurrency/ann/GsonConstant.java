package com.cluttered.cryptocurrency.ann;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonConstant {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
}
