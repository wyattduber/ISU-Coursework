module prime_circuit(P, W, X, Y, Z);

	input W, X, Y, Z;
	output P;
	
	assign P = (~X & Y & Z) | (~W & Y & Z) | (~W & X & Z) | (X & ~Y & Z);
	
endmodule



