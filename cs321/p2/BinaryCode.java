import java.util.HashMap;

public class BinaryCode {
    protected String code;
    public String instruction;
    public String fullInstruction;
    public int branch;
    public String instructionWithoutBranch;
    protected HashMap<String, String> opcodes = (HashMap<String, String>) FileOperation.HashMapFromTextFile();
    protected HashMap<Integer,String> conditions = new HashMap<>();
    public BinaryCode(String code){
        this.code=code;

        conditions.put(0,"EQ");
        conditions.put(1,"NE");
        conditions.put(2,"HS");
        conditions.put(3,"LO");
        conditions.put(4,"MI");
        conditions.put(5,"PL");
        conditions.put(6,"VS");
        conditions.put(7,"VC");
        conditions.put(8,"HI");
        conditions.put(9,"LS");
        conditions.put(10,"GE");
        conditions.put(11,"LT");
        conditions.put(12,"GT");
        conditions.put(13,"LE");
    }
    public String addLabel(String branchLabel){
        return instructionWithoutBranch+" "+branchLabel;
    }
    public void bin_to_instruction(){
        String btype=code.substring(0,6);
        String cbtype=code.substring(0,8);
        String itype=code.substring(0,10);
        String rtype=code.substring(0,11);

        String result1=opcodes.get(btype);
        String result2=opcodes.get(cbtype);
        String result3=opcodes.get(itype);
        String result4=opcodes.get(rtype);

        if(result1!=null){
            boolean negative= code.charAt(7) == '1';
            if(result1.equals("B.")){
                int rn=Integer.parseInt((code.substring(27,32)),2);
                String cond=conditions.get(rn);
                branch=Integer.parseInt((code.substring(6,27)),2);
                if(negative){
                    branch=-Integer.parseInt(twosCompliment(code.substring(6,27)),2);
                }
                instruction=result1;
                instructionWithoutBranch=result1+cond;
                fullInstruction=(result1+cond+" "+branch);
            }
            else{
                branch=Integer.parseInt((code.substring(6,32)),2);
                if(negative){
                    branch=-Integer.parseInt(twosCompliment(code.substring(6,32)),2);
                }
                instruction=result1;
                instructionWithoutBranch=result1;
                fullInstruction=result1+" "+branch;
            }

        }

        else if(result2!=null){
            boolean negative= code.charAt(9) == '1';
            int rn=Integer.parseInt((code.substring(27,32)),2);
            branch=Integer.parseInt((code.substring(8,27)),2);
            if(negative){
                branch=-Integer.parseInt(twosCompliment(code.substring(8,27)),2);
            }
            instruction=result2;
            instructionWithoutBranch=result2+" X"+rn;
            fullInstruction=result2+" X"+ rn+" "+branch;
        }

        else if(result3!=null){//Immediate commands like addi, subi, etc
            int immediate=Integer.parseInt((code.substring(11,22)),2);
            int register2=Integer.parseInt((code.substring(27,32)),2);
            int register3=Integer.parseInt((code.substring(22,27)),2);
            instruction=result3;
            fullInstruction=(result3+" X"+register2+", X"+register3+", #"+immediate );
        }

        else if(result4!=null){
            if(!result4.equals("DUMP")&&!result4.equals("HALT")&&!result4.equals("PRNT")
                    &&!result4.equals("PRNL")&&!result4.equals("LDUR")
                    &&!result4.equals("STUR")&&!result4.equals("LSL")&&!result4.equals("BR")) {

                int register3 = Integer.parseInt((code.substring(11, 16)), 2);
                int register2 = Integer.parseInt((code.substring(22, 27)), 2);
                int register1 = Integer.parseInt((code.substring(27, 32)), 2);
                instruction=result4;
                fullInstruction=(result4 + " X" + register1 + ", X" + register2 + ", X" + register3);
            }
            else if(result4.equals("PRNT")){

                int register1 = Integer.parseInt((code.substring(27, 32)), 2);
                fullInstruction=(result4 + " X" + register1);
            }
            else if(result4.equals("BR")){

                int rn=Integer.parseInt((code.substring(22, 27)), 2);
                fullInstruction=(result4 + " X" + rn);
            }
            else if(result4.equals("LDUR")||result4.equals("STUR")){

                int address=Integer.parseInt((code.substring(11, 20)), 2);
                int rn=Integer.parseInt((code.substring(22, 27)), 2);
                int rt=Integer.parseInt((code.substring(27, 32)), 2);
                fullInstruction=(result4 + " X" + rt + ", [X" + rn + ", #" +address+"]");
            }
            else if(result4.equals("LSL")){

                int shamt=Integer.parseInt((code.substring(16, 22)), 2);
                int rn=Integer.parseInt((code.substring(22, 27)), 2);
                int rd=Integer.parseInt((code.substring(27, 32)), 2);
                fullInstruction=(result4 + " X" + rn + ", X" + rd + ", #" + shamt);
            }
            else{
                fullInstruction=(result4);
            }
        }
    }
    public static String twosCompliment(String binaryCode){
        StringBuilder onesComplement= new StringBuilder();
        String twosComplement;
        boolean isFound=false;
        for(int i=0;i<binaryCode.length();i++){
            onesComplement.append(flipBinary(binaryCode.charAt(i)));
        }
        StringBuilder b = new StringBuilder(onesComplement.toString());
        for(int j=onesComplement.length()-1;j>0;j--){
            if(onesComplement.charAt(j)=='1'){
                b.setCharAt(j,'0');
            }
            else{
                b.setCharAt(j,'1');
                isFound=true;
                break;
            }
        }
        if(!isFound){
            b.append("1",0,7);
        }
        twosComplement=b.toString();
        return twosComplement;
    }
    private static char flipBinary(char charAt) {
        if (charAt=='1'){
            return '0';
        }
        else{
            return '1';
        }
    }
}
