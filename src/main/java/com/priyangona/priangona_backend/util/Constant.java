package com.priyangona.priangona_backend.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Constant {
    public static final String ADMIN_PAGE = "admin/adminIndex";
    public static final String USER_PAGE = "index";

    private static final String ROOT_DIRECTORY = "";
    public static final Path ROOT_PATH = Paths.get(ROOT_DIRECTORY).toAbsolutePath();
    public static final String USER_IMAGE_PATH = "/priyangona/user/";
    public static final String CATEGORY_IMAGE_PATH = "/priyangona/category/";
    public static final String PRODUCT_IMAGE_PATH = "/priyangona/product/";
}
