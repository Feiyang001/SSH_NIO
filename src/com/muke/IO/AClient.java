package com.muke.IO;

import java.io.IOException;

public class AClient {
    public static void main(String[] args) throws IOException {
        new NIOClient().start(9999,"Aclient");
    }
}
