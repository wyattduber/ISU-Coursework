
import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.HashMap;

public class LEGv8Disassembler {

    public static void main(String[] args) throws IOException {
        //Argument Sanity Checks
        if (args.length == 0) {
            System.out.println("Missing Filename Argument!");
            return;
        }

        File file = new File(args[0]);
        ArrayList<String> toPrint= new ArrayList<>();
        //Create label hashmap for later use
        HashMap<Integer,String> labels = new HashMap<>();//hashmap of labels to be filled in

        //Reading in the file:
        byte[] fileBytes;
        try {
            fileBytes = Files.readAllBytes(file.toPath()); //Reads in all bytes from the document
        } catch (NoSuchFileException e) {
            System.out.println("File not found!");
            return;
        }
        ArrayList<String> binarycodes = new ArrayList<>(); //Arraylist of the actual 32 bit binary codes for analysis
        for(int i=0;i<fileBytes.length;i+=4) { //Read through every byte in the fileBytes array. But increment by 4 since we are reading in 8 byte increments
            String output = "";
            output += String.format("%8s", Integer.toBinaryString(fileBytes[i] & 0xFF)).replace(' ', '0');
            output += String.format("%8s", Integer.toBinaryString(fileBytes[i + 1] & 0xFF)).replace(' ', '0');
            output += String.format("%8s", Integer.toBinaryString(fileBytes[i + 2] & 0xFF)).replace(' ', '0');
            output += String.format("%8s", Integer.toBinaryString(fileBytes[i + 3] & 0xFF)).replace(' ', '0');
            binarycodes.add(output);
        }
        //parsing binary codes and disassembling
        int lineNum=-1;
        for(String code : binarycodes){

            //line numbering for ease of use
            lineNum++;

            //working with binary codes now
            BinaryCode newcode= new BinaryCode(code);
            newcode.bin_to_instruction();
            if (newcode.branch!=0){ //set to 0 as you can never call a branch in the same location as your command.
                String label=labeling(newcode.branch,lineNum,labels);
                toPrint.add(lineNum,newcode.addLabel(label));
            }
            else{
                toPrint.add(lineNum,newcode.fullInstruction);
            }
        }
        addInLabels(labels,toPrint);
        for (String s : toPrint) {
            System.out.println(s);
        }

    }


    public static String labeling(int branchCode, int currentlocation, HashMap<Integer, String> labels){
        int actualLocation=branchCode+currentlocation;
        if(labels.get(actualLocation)==null){
            labels.put(actualLocation,"Label"+(labels.size()+1)+":");
        }
        return labels.get(actualLocation);
    }

    public static void addInLabels(HashMap<Integer,String> labels,ArrayList<String> toPrint){
        for(int i=0;i<toPrint.size()+labels.size();i++){
            if(labels.get(i)!=null){
                toPrint.add(i,labels.get(i));
                labels.remove(i);
                for(int j=toPrint.size()+labels.size()-1;j>0;j--){
                    if(labels.get(j)!=null){
                        labels.put(j+1,labels.get(j));
                        labels.remove(j);
                    }
                }
            }
        }
    }
}
