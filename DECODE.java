import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;


public class DECODE {

    public class Utility {
	
        public static BufferedReader openFile(String filename) {
            try { return new BufferedReader(new FileReader(filename)); }
            catch (IOException e) { return null; }}
        
        public static void closeFile(BufferedReader reader) {
            try { reader.close(); }
            catch (IOException e) { }}
        
        public static String readLine(BufferedReader reader) {
            try { return reader.readLine(); } 
            catch (IOException e) { return null; }}}

    // METHOD TO GET THE NO. OF LINES OF TEXT :

    public int rf(String path){

        int num = 0;

        String data = null;

    try {
        File Obj = new File(path);
        Scanner Reader = new Scanner(Obj);
        while (Reader.hasNextLine()) {
            data = Reader.nextLine();
        num = num+1;}
        Reader.close();}

    catch (FileNotFoundException e) {
        System.out.println("An error has occurred.");
        e.printStackTrace();}

    return num;}

    // METHOD TO READ A FILE :

    public String readfile(String path, int line){

        DECODE run = new DECODE();

        String data = null;
        int lineNumber;

        try {
            FileReader readfile = new FileReader(path);
            BufferedReader readbuffer = new BufferedReader(readfile);
            for (lineNumber = 1; lineNumber <2*(run.rf(path)); lineNumber++) {
              if (lineNumber == line) {
                data = readbuffer.readLine();
              } else
                readbuffer.readLine();}

          } catch (IOException e) {
            e.printStackTrace();}

    return data;}

    // METHOD FOR DATA TYPE CASTING :
    
    public int[] conv(String data){
    
        String[] str = data.replaceAll("\\[", "").replaceAll("\\]",
                 "").replaceAll("\\s", "").split(",");
        int[] Int_array = new int[str.length];
        for (int i = 0; i < str.length; i++) {
        try {
        Int_array[i] = Integer.parseInt(str[i]);
        } catch (NumberFormatException nfe) {};}
        return Int_array;}


    // METHOD TO ACCESS THE READ FILE - HASH :

    public int[] Hash(String path1, int line){

        DECODE run = new DECODE();
        // C:\\Users\\Venu Pulagam\\Desktop\\Hashind.txt
        String hashdata = run.readfile(path1, line);
        int[] hash = run.conv(hashdata);
			
    return hash;}


    // METHOD TO ACCESS THE READ FILE - REPEAT :

    public int[] Repeat(String path2, int line){

        DECODE run = new DECODE();
        
        // C:\\Users\\Venu Pulagam\\Desktop\\Repeat.txt
        String repeatdata = run.readfile(path2, line);
        int[] repeat = run.conv(repeatdata);

    return repeat;}


    // METHOD TO GET THE ASCII KEYS OF THE FILE :

    public int[] getasc(int[] hash, int[] repeat){

        int x=0;
        
        for (int i=0; i<hash.length; i++){
            while(x < repeat[i]){
                hash[i] = hash[i] + hash.length;
            x = x+1;}
        x=0;}

    return hash;}

    // METHOD TO DECRYPT THE FILE :

    public String tolet(int[] ascii){

        char[] letters = new char[ascii.length];

        for (int i=0; i<ascii.length; i++){
            letters [i] = (char) ascii[i];}

        String str = String.valueOf(letters);
        return str;}

    // MAIN METHOD :
    
    public static void main(String[] args){

        DECODE run = new DECODE();

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the path of the Hashed file you want to decrypt : ");
        String path1 = sc.nextLine();

        int linecount = run.rf(path1);

        System.out.println("\nEnter the path of the Repat value file you want to decrypt : ");
        String path2 = sc.nextLine();

        File file = new File("C:\\Users\\Venu Pulagam\\Desktop\\Result.txt");
        file.delete();

        for (int line=1; line<=linecount; line++){

        int[] hash = run.Hash(path1, line);
        int[] repeat = run.Repeat(path2, line);
        int[] hashult = run.getasc(hash, repeat);
        String str = run.tolet(hashult);

        File log = new File("C:\\Users\\Venu Pulagam\\Desktop\\Result.txt");

        try {
            if(!log.exists()){
            log.createNewFile();}

            FileWriter fileWriter = new FileWriter(log, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(str);
            bufferedWriter.write("\n");
            bufferedWriter.close();
        
            if (line==linecount){
            System.out.println("\nDECRYPRION SUCCESSFUL !");}}
            
            catch(IOException e) {
            System.out.println("COULD NOT LOG!!");}
    
            catch(Exception e){
                System.out.println(e);}}}}