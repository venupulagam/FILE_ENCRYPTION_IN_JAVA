import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileReader;

public class ENCODE {

// METHOD TO READ A FILE :

    public String[] readfile(String path){

        String data = null;
        int num = 0;
        int i=0;

        try {
            File Obj = new File(path);
            Scanner Reader = new Scanner(Obj);
            while (Reader.hasNextLine()) {
                data = Reader.nextLine();
            num = num+1;}} 
    
        catch (FileNotFoundException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();}

        String[] strdata = new String[num];

        try {
            File Obj = new File(path);
        Scanner Reader = new Scanner(Obj);
            while (Reader.hasNextLine()) {
                strdata[i] = Reader.nextLine();
                i = i+1;}}
    
            catch (FileNotFoundException e) {
        System.out.println("An error has occurred.");
        e.printStackTrace();}

    return strdata;}

// METHOD TO GET ASCII KEYS :

    public int[] ascii(String word){
        char[] a = word.toCharArray();
        int[] ascii = new int[a.length];
        for (int i=0; i<a.length; i++){
            ascii[i] = (int) a[i];}
    return ascii ;}

// SUS :

    public int sus(int num, int len){

        int rep = 0;

        while (num>=0){
            num = num-len;
            rep = rep+1;}

            rep = rep-1;
            return rep;}

// MODULUS FUNCTION :

    public int[] mod(String word){

        ENCODE has = new ENCODE();
        int[] a = has.ascii(word);
        int[] num = new int[a.length];

        for (int i=0; i<a.length; i++){
            num[i] = has.sus(a[i],a.length);}

        return num;}

// HASH FUNCTION :

    public int[] gethash(String word){

        ENCODE has = new ENCODE();
        int[] a = has.ascii(word);

        int[] hash = new int[a.length];
        int[] sh = new int[a.length];
        for (int i=0; i<a.length; i++){
            hash[i] =  a[i] % a.length;
            sh[hash[i]] = a[i];}
    return hash;}

// METHOD TO GET THE ARRAY OF HASH INDICES :

    public int[] hashin(String word){

        ENCODE has = new ENCODE();
        int[] asc = has.ascii(word);
        int[] hash = has.gethash(word);
        int[] hashind = new int[hash.length];

        for (int i=0; i<hash.length; i++){
            for (int j=0; j<hash.length; j++){
                if (asc[i] == hash[j]){
                    hashind[i] = j;}}}
        return hashind;}

// METHOD TO GET THE ORIGINAL CHAR :

    public char[] orgchar(String word){

        ENCODE has = new ENCODE();
        int[] hash = has.gethash(word);
        int[] hashin = has.hashin(word);

        char[] ch = new char[hash.length]; 
        for (int i=0; i<hash.length; i++){
                ch[i] = (char) hash[hashin[i]];}
        return ch;}

// MAIN METHOD :

    public static void main(String[] args){

        ENCODE hash = new ENCODE();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the path of the file you want to encrypt : ");  
        String path = sc.nextLine();

        String[] word = hash.readfile(path);

        int[] lines = new int[word.length];

        File file = new File("C:\\Users\\Venu Pulagam\\Desktop\\Hashind.txt");
        file.delete();

        File file2 = new File("C:\\Users\\Venu Pulagam\\Desktop\\Repeat.txt");
        file2.delete();

        for (int i=0; i<word.length; i++){
            char[] a = word[i].toCharArray();
            int[] asm = hash.ascii(word[i]);
            int[] hasha = hash.gethash(word[i]);
            int[] hashc = hash.mod(word[i]);

            File log = new File("C:\\Users\\Venu Pulagam\\Desktop\\Hashind.txt");
            File log2 = new File("C:\\Users\\Venu Pulagam\\Desktop\\Repeat.txt");

        try{
            if(!log.exists()){
            log.createNewFile();}

            if(!log.exists()){
            log.createNewFile();}

        FileWriter fileWriter = new FileWriter(log, true);
        FileWriter fileWriter2 = new FileWriter(log2, true);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(Arrays.toString(hasha));
        bufferedWriter.write("\n");
        bufferedWriter.close();

        BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
        bufferedWriter2.write(Arrays.toString(hashc));
        bufferedWriter2.write("\n");
        bufferedWriter2.close();

        if (i==word.length-1){
        System.out.println("\nENCRYPTION SUCCESSFUL !");}

        } catch(IOException e) {
        System.out.println("COULD NOT LOG!!");}

        catch(Exception e){
            System.out.println(e);}
        }}}

        /** ------      C:\\Users\\Venu Pulagam\\Desktop\\test.txt     ------**/