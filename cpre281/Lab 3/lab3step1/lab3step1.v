module lab3step1(F,C,G,W,A);

input F,C,G,W;
output A;

wire cg,wg,fcg,fwg;

or(cg,C,G);
or(wg,g,W);
nand(fcg,cg,F);
nand(fwg,wg,F);
xor(A,fwg,fcg);
	
endmodule