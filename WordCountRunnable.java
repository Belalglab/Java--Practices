package edu.weber.cs3230.GUI.Multithreading.homework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Counts how many words in a file.
 */
public class WordCountRunnable implements Runnable {
   public static int threadCount = 0;
   public static Lock threadCountLock = new ReentrantLock();
   public static int combinedWordCount = 0;
   public static Lock combinedWordCountLock = new ReentrantLock();

   private String filename;

   /**
    * Constructs a WordCountRunnable object with a file name.
    * @param aFilename the file name that needs to count word
    */
   public WordCountRunnable(String aFilename) {
      filename = aFilename;
   }

   public void run() {
      // Increment thread count
      threadCountLock.lock();
      try {
         threadCount++;
      } finally {
         threadCountLock.unlock();
      }

      int count = 0;
      try {
         Scanner in = new Scanner(new FileInputStream(filename));
         while (in.hasNext()) {
            in.next();
            count++;
         }
         in.close();
      } catch (FileNotFoundException e) {
         System.out.println(filename + " not found!");
         // Decrement thread count and possibly print combined count
         decrementThreadCountAndPrintCombined();
         return;
      }

      // Update combined word count
      combinedWordCountLock.lock();
      try {
         combinedWordCount += count;
      } finally {
         combinedWordCountLock.unlock();
      }
      decrementThreadCountAndPrintCombined();
   }

   private void decrementThreadCountAndPrintCombined() {
      threadCountLock.lock();
      try {
         threadCount--;
         if (threadCount == 0) {
            // Last active thread
            System.out.println("Combined Total: " + combinedWordCount);
         }
      } finally {
         threadCountLock.unlock();
      }
   }

   public static int getThreadCount() {
      return threadCount;
   }

   public static int getCombinedWordCount() {
      return combinedWordCount;
   }
}
