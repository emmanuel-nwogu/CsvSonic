package com.company;

import java.io.*;

/**
 * Problem statement: You work for a company that has certain trade secrets in audio format.
 * However, we would prefer storing the audio files as lightly encrypted text files that could be easily concealed as
 * “dump” files. Using XOR encoding/decoding, implement a function that reads a file as a byte buffer, xors each byte in
 * this buffer a given number of times using a given key, and stores the resulting buffer as a file using a given output
 * path.
 */
public class Main {

    /**
     * This method XORs {@code toBeDecoded} with {@code key}, then XORs the result with {@code key} and so on.
     * This XORing with {@code key} is performed {@code numberOfXors} times.
     *
     * @param toBeDecoded  the first byte to be XORed with {@code key}
     * @param key          the key to be XORed with for each XOR operation
     * @param numberOfXors the number of times the key will be used for XOR operations
     * @return the result of the XOR operations
     */
    private static byte xorByteWithKey(byte toBeDecoded, int key, int numberOfXors) {
        byte xored = 0;
        for (int i = 0; i < numberOfXors; i++) {
            xored = (byte) (toBeDecoded ^ key);
        }
        return xored;
    }

    /**
     * Reads in the file at {@code sourceFilePath} as a byte buffer, XORs this buffer with {@code key}
     * {@code numberOfXors} times, and writes this buffer to a file at the path specified by
     * {@code  destinationFilePath}.
     *
     * @param sourceFilePath      The path of the file to be XOR-encoded/decoded and written to another file
     * @param destinationFilePath The path of the file where the XOR-encoded/decoded buffer will be stored
     * @param key the key to be used for XOR-encoding/decoding
     * @param numberOfXors the number of times the key will be used for XOR operations
     *
     * @return true when the method is successful and false otherwise.
     */
    public static boolean encryptSourceFileAsDestinationFile(String sourceFilePath, String destinationFilePath,
                                                             int key, int numberOfXors) {
        FileInputStream sourceFileStream;
        try {
            sourceFileStream = new FileInputStream(sourceFilePath);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.printf("File at %s not found!\n", sourceFilePath);
            return false;
        }
        byte[] sourceBuffer;
        try {
            sourceBuffer = sourceFileStream.readAllBytes();
        } catch (IOException ioException) {
            System.out.printf("Reading bytes from %s failed!\n", sourceFilePath);
            return false;
        }
        // XOR encode every byte in the source file buffer
        for (int i = 0; i < sourceBuffer.length; i++) {
            sourceBuffer[i] = xorByteWithKey(sourceBuffer[i], key, numberOfXors);
        }
        FileOutputStream destinationFileStream;
        try {
            File destinationFile = new File(destinationFilePath);
            destinationFileStream = new FileOutputStream(destinationFile);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.printf("File at %s can not be opened!\n", fileNotFoundException);
            return false;
        }
        try {
            destinationFileStream.write(sourceBuffer);
        } catch (IOException ioException) {
            System.out.printf("Writing to the file at %s failed!\n", ioException);
            return false;
        }
        try {
            sourceFileStream.close();
        } catch (IOException ioException) {
            System.out.println("Closing the destination file stream failed!");
            return false;
        }
        try {
            destinationFileStream.close();
        } catch (IOException ioException) {
            System.out.println("Closing the destination file stream failed!");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        boolean success = encryptSourceFileAsDestinationFile("resources/whispered_secret.wav", "output/out.txt", 547, 34);
        System.out.printf("Success: %b\n", success);
    }
}
