
package vigenere;

public class Vigenere {
    
    // Таблица Виженера
    private static final char[][] mTable;
    
    // Инициализация таблицы
    static {
        int length = 'я'-'а' + 1;
        mTable = new char[length][length];
        
        for (int j=0; j<mTable[0].length; j++)
            mTable[0][j] = (char)('а' + j);
        
        for (int i=1; i<mTable.length; i++) {
            for (int j=0; j<mTable[i].length; j++) {
                mTable[i][j] = (char)('а' + ((j + i) % length));
            }
        }
    }
    
    // Шифровать символ
    private static char EncryptChar(char dataChar, char keyChar) {
        if (!Contains(dataChar))
            return dataChar;
        
        return mTable[dataChar - 'а'][keyChar - 'а'];
    }
    
    // Расшифровать символ
    private static char DecryptChar(char dataChar, char keyChar) {
        if (!Contains(dataChar))
            return dataChar;
        
        int idx = keyChar - 'а';
        for (char[] mTable1 : mTable) {
            if (mTable1[idx] == dataChar) {
                return mTable1[0];
            }
        }
        
        throw new RuntimeException("Not found");
    }
    
    // true, если таблица содержит символ
    private static boolean Contains(char c) {
        for (int j=0; j<mTable[0].length; j++) {
            if (mTable[0][j] == c)
                return true;
        }
        return false;
    }
    
    // Шифровать
    public static String Encrypt(String text, String key) {
        if (text.isEmpty())
            throw new RuntimeException("Text is empty!");
        
        if (key.isEmpty())
            throw new RuntimeException("Key is empty!");
        
        String result = "";
        for (int i=0; i<text.length(); i++) {
            result += EncryptChar(text.charAt(i), key.charAt(i % key.length()));
        }
        return result;
    }
    
    // Расшифровать
    public static String Decrypt(String text, String key) {
        if (text.isEmpty())
            throw new RuntimeException("Text is empty!");
        
        if (key.isEmpty())
            throw new RuntimeException("Key is empty!");
        
        String result = "";
        for (int i=0; i<text.length(); i++) {
            result += DecryptChar(text.charAt(i), key.charAt(i % key.length()));
        }
        return result;
    }
}
