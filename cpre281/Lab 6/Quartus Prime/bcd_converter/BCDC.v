module bcdc(C0, S3, S2, S1, S0, N2X3, N2X2, N2X1, N2X0, N1X3, N1X2, N1X1, N1X0);

	input C0, S3, S2, S1, S0;
	output N2X3, N2X2, N2X1, N2X0, N1X3, N1X2, N1X1, N1X0;
	
	assign N2X3 = 'b0;
	assign N2X2 = 'b0;
	assign N2X1 = (C0 & S3) | (C0 & S2);
	assign N2X0 = (~C0 & S3 & S1) | (~C0 & S2 & S1) | (S3 & S2 & S1) | (C0 & ~S3 & ~S2);
	assign N1X3 = (~C0 & S3 & ~S2 & ~S1) | (C0 & ~S3 & ~S2 & S1) | (C0 & S3 & S2 & ~S1);
	assign N1X2 = (~C0 & ~S3 & S2) | (~C0 & S2 & S1) | (C0 & ~S2 & ~S1) | (C0 & S3 & ~S2);
	assign N1X1 = (~C0 & ~S3 & S1) | (~S3 & S2 & S1) | (~C0 & S3 & S2 & ~S1) | (C0 & ~S3 & ~S2 & ~S1) | (C0 & S3 & ~S2 & S1);
	assign N1X0 = S0;
	
endmodule