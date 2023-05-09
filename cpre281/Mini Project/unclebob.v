module unclebob(B, W, X, Y, Z);

	input W, X, Y, Z;
	output B;
	
	assign B = (W & X & Z) | (W & Y & Z) | (X & ~Y & Z);
	
endmodule









