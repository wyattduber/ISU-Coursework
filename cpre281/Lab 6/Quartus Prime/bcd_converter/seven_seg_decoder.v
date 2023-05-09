module seven_seg_decoder (X3, X2, X1, X0, A, B, C, D, E, F, G);
input X3, X2, X1, X0;
output A, B, C, D, E, F, G;
reg A, B, C, D, E, F, G;
reg [6:0] z;
	
always @(X0 or X1 or X2 or X3)
begin
	case ({X0,X1,X2,X3})

		4'b0000: z = 7'b0000001;
		4'b0001: z = 7'b0110000;
		4'b0010: z = 7'b1101101; 
		4'b0011: z = 7'b1111001;
		4'b0100: z = 7'b0110011;
		4'b0101: z = 7'b1011011;  
		4'b0110: z = 7'b1011111;
		4'b0111: z = 7'b1110000;
		4'b1000: z = 7'b111111;
		4'b1001: z = 7'b1111011;
		4'b1010: z = 7'b1110111; 
		4'b1011: z = 7'b0011111;
		4'b1100: z = 7'b1001110;
		4'b1101: z = 7'b0111101;
		4'b1110: z = 7'b1001111;
		4'b1111: z = 7'b1000111;
				
	endcase

	A = z[0];
	B = z[1];
	C = z[2];
	D = z[3];
	E = z[4];
	F = z[5];
	G = z[6];
 
end 

endmodule