// Copyright (C) 2020  Intel Corporation. All rights reserved.
// Your use of Intel Corporation's design tools, logic functions 
// and other software and tools, and any partner logic 
// functions, and any output files from any of the foregoing 
// (including device programming or simulation files), and any 
// associated documentation or information are expressly subject 
// to the terms and conditions of the Intel Program License 
// Subscription Agreement, the Intel Quartus Prime License Agreement,
// the Intel FPGA IP License Agreement, or other applicable license
// agreement, including, without limitation, that your use is for
// the sole purpose of programming logic devices manufactured by
// Intel and sold by Intel or its authorized distributors.  Please
// refer to the applicable agreement for further details, at
// https://fpgasoftware.intel.com/eula.

// PROGRAM		"Quartus Prime"
// VERSION		"Version 20.1.0 Build 711 06/05/2020 SJ Standard Edition"
// CREATED		"Wed Oct 14 22:03:52 2020"

module lab6step0(
	Cin,
	N2,
	N3,
	A2,
	B2,
	C2,
	D2,
	E2,
	F2,
	G2,
	A3,
	B3,
	C3,
	D3,
	E3,
	F3,
	G3,
	A1,
	B1,
	C1,
	D1,
	E1,
	F1,
	G1,
	A0,
	B0,
	C0,
	D0,
	E0,
	F0,
	G0
);


input wire	Cin;
input wire	[3:0] N2;
input wire	[3:0] N3;
output wire	A2;
output wire	B2;
output wire	C2;
output wire	D2;
output wire	E2;
output wire	F2;
output wire	G2;
output wire	A3;
output wire	B3;
output wire	C3;
output wire	D3;
output wire	E3;
output wire	F3;
output wire	G3;
output wire	A1;
output wire	B1;
output wire	C1;
output wire	D1;
output wire	E1;
output wire	F1;
output wire	G1;
output wire	A0;
output wire	B0;
output wire	C0;
output wire	D0;
output wire	E0;
output wire	F0;
output wire	G0;

wire	SYNTHESIZED_WIRE_0;
wire	SYNTHESIZED_WIRE_1;
wire	SYNTHESIZED_WIRE_2;
wire	SYNTHESIZED_WIRE_3;
wire	SYNTHESIZED_WIRE_4;
wire	SYNTHESIZED_WIRE_5;
wire	SYNTHESIZED_WIRE_6;
wire	SYNTHESIZED_WIRE_7;
wire	SYNTHESIZED_WIRE_8;
wire	SYNTHESIZED_WIRE_9;
wire	SYNTHESIZED_WIRE_10;
wire	SYNTHESIZED_WIRE_11;
wire	SYNTHESIZED_WIRE_12;





bcdc	b2v_inst(
	.C0(SYNTHESIZED_WIRE_0),
	.S3(SYNTHESIZED_WIRE_1),
	.S2(SYNTHESIZED_WIRE_2),
	.S1(SYNTHESIZED_WIRE_3),
	.S0(SYNTHESIZED_WIRE_4),
	.N2X3(SYNTHESIZED_WIRE_8),
	.N2X2(SYNTHESIZED_WIRE_7),
	.N2X1(SYNTHESIZED_WIRE_6),
	.N2X0(SYNTHESIZED_WIRE_5),
	.N1X3(SYNTHESIZED_WIRE_12),
	.N1X2(SYNTHESIZED_WIRE_11),
	.N1X1(SYNTHESIZED_WIRE_10),
	.N1X0(SYNTHESIZED_WIRE_9));


seven_seg_decoder	b2v_inst0(
	.X0(SYNTHESIZED_WIRE_5),
	.X1(SYNTHESIZED_WIRE_6),
	.X2(SYNTHESIZED_WIRE_7),
	.X3(SYNTHESIZED_WIRE_8),
	.A(A1),
	.B(B1),
	.C(C1),
	.D(D1),
	.E(E1),
	.F(F1),
	.G(G1));


seven_seg_decoder	b2v_inst1(
	.X0(N3[0]),
	.X1(N3[1]),
	.X2(N3[2]),
	.X3(N3[3]),
	.A(A2),
	.B(B2),
	.C(C2),
	.D(D2),
	.E(E2),
	.F(F2),
	.G(G2));


adder_4bit	b2v_inst2(
	.X3(N3[3]),
	.Y3(N2[3]),
	.X2(N3[2]),
	.Y2(N2[2]),
	.X1(N3[1]),
	.Y1(N2[1]),
	.X0(N3[0]),
	.Y0(N2[0]),
	.Ci(Cin),
	.S0(SYNTHESIZED_WIRE_0),
	.S1(SYNTHESIZED_WIRE_1),
	.S2(SYNTHESIZED_WIRE_2),
	.S3(SYNTHESIZED_WIRE_3),
	.Co(SYNTHESIZED_WIRE_4));


seven_seg_decoder	b2v_inst3(
	.X0(SYNTHESIZED_WIRE_9),
	.X1(SYNTHESIZED_WIRE_10),
	.X2(SYNTHESIZED_WIRE_11),
	.X3(SYNTHESIZED_WIRE_12),
	.A(A0),
	.B(B0),
	.C(C0),
	.D(D0),
	.E(E0),
	.F(F0),
	.G(G0));


seven_seg_decoder	b2v_inst4(
	.X0(N2[0]),
	.X1(N2[1]),
	.X2(N2[2]),
	.X3(N2[3]),
	.A(A3),
	.B(B3),
	.C(C3),
	.D(D3),
	.E(E3),
	.F(F3),
	.G(G3));


endmodule
