package com.jumper.io;

import java.io.File;

public class FilesTest {

    public static void main(String[] args) throws Exception {
//        Path path = Paths.get("/test/test");
//        Files.createDirectory(path);
//        Files.createDirectories(path);
        String path = "D:/opt/test/test/a.txt";
        File file = new File(path);
        if(!file.getParentFile().exists()){
            boolean mkdirs = file.getParentFile().mkdirs();
            System.out.println(mkdirs);
        }
    }

}
