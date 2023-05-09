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
// CREATED		"Sat Nov 21 22:07:35 2020"

module regfile(
	RP2,
	RP1,
	RP0,
	RQ2,
	RQ1,
	RQ0,
	WA2,
	WA1,
	WA0,
	WR,
	CLRN,
	CLK,
	LD_DATA,
	DATAP3,
	DATAP2,
	DATAP1,
	DATAP0,
	DATAQ3,
	DATAQ2,
	DATAQ1,
	DATAQ0
);


input wire	RP2;
input wire	RP1;
input wire	RP0;
input wire	RQ2;
input wire	RQ1;
input wire	RQ0;
input wire	WA2;
input wire	WA1;
input wire	WA0;
input wire	WR;
input wire	CLRN;
input wire	CLK;
input wire	[3:0] LD_DATA;
output wire	DATAP3;
output wire	DATAP2;
output wire	DATAP1;
output wire	DATAP0;
output wire	DATAQ3;
output wire	DATAQ2;
output wire	DATAQ1;
output wire	DATAQ0;

wire	SYNTHESIZED_WIRE_0;
wire	[3:0] SYNTHESIZED_WIRE_24;
wire	[3:0] SYNTHESIZED_WIRE_25;
wire	[3:0] SYNTHESIZED_WIRE_26;
wire	[3:0] SYNTHESIZED_WIRE_27;
wire	[3:0] SYNTHESIZED_WIRE_28;
wire	[3:0] SYNTHESIZED_WIRE_29;
wire	[3:0] SYNTHESIZED_WIRE_30;
wire	[3:0] SYNTHESIZED_WIRE_31;
wire	SYNTHESIZED_WIRE_9;
wire	SYNTHESIZED_WIRE_10;
wire	SYNTHESIZED_WIRE_11;
wire	SYNTHESIZED_WIRE_12;
wire	SYNTHESIZED_WIRE_13;
wire	SYNTHESIZED_WIRE_14;
wire	SYNTHESIZED_WIRE_15;





Decoder3to8	b2v_inst(
	.EN(WR),
	.W2(WA2),
	.W1(WA1),
	.W0(WA0),
	.Y0(SYNTHESIZED_WIRE_15),
	.Y1(SYNTHESIZED_WIRE_0),
	.Y2(SYNTHESIZED_WIRE_9),
	.Y3(SYNTHESIZED_WIRE_14),
	.Y4(SYNTHESIZED_WIRE_10),
	.Y5(SYNTHESIZED_WIRE_11),
	.Y6(SYNTHESIZED_WIRE_12),
	.Y7(SYNTHESIZED_WIRE_13));


reg4b	b2v_inst1(
	.LOAD(SYNTHESIZED_WIRE_0),
	.CLK(CLK),
	.CLRN(CLRN),
	.IN(LD_DATA),
	.Out(SYNTHESIZED_WIRE_25));


Mux8_4b	b2v_inst10(
	.S2(RP2),
	.S1(RP1),
	.S0(RP0),
	.W0(SYNTHESIZED_WIRE_24),
	.W1(SYNTHESIZED_WIRE_25),
	.W2(SYNTHESIZED_WIRE_26),
	.W3(SYNTHESIZED_WIRE_27),
	.W4(SYNTHESIZED_WIRE_28),
	.W5(SYNTHESIZED_WIRE_29),
	.W6(SYNTHESIZED_WIRE_30),
	.W7(SYNTHESIZED_WIRE_31)
	);


reg4b	b2v_inst2(
	.LOAD(SYNTHESIZED_WIRE_9),
	.CLK(CLK),
	.CLRN(CLRN),
	.IN(LD_DATA),
	.Out(SYNTHESIZED_WIRE_26));


reg4b	b2v_inst3(
	.LOAD(SYNTHESIZED_WIRE_10),
	.CLK(CLK),
	.CLRN(CLRN),
	.IN(LD_DATA),
	.Out(SYNTHESIZED_WIRE_28));


reg4b	b2v_inst4(
	.LOAD(SYNTHESIZED_WIRE_11),
	.CLK(CLK),
	.CLRN(CLRN),
	.IN(LD_DATA),
	.Out(SYNTHESIZED_WIRE_29));


reg4b	b2v_inst5(
	.LOAD(SYNTHESIZED_WIRE_12),
	.CLK(CLK),
	.CLRN(CLRN),
	.IN(LD_DATA),
	.Out(SYNTHESIZED_WIRE_30));


reg4b	b2v_inst6(
	.LOAD(SYNTHESIZED_WIRE_13),
	.CLK(CLK),
	.CLRN(CLRN),
	.IN(LD_DATA),
	.Out(SYNTHESIZED_WIRE_31));


reg4b	b2v_inst7(
	.LOAD(SYNTHESIZED_WIRE_14),
	.CLK(CLK),
	.CLRN(CLRN),
	.IN(LD_DATA),
	.Out(SYNTHESIZED_WIRE_27));


reg4b	b2v_inst8(
	.LOAD(SYNTHESIZED_WIRE_15),
	.CLK(CLK),
	.CLRN(CLRN),
	.IN(LD_DATA),
	.Out(SYNTHESIZED_WIRE_24));


Mux8_4b	b2v_inst9(
	.S2(RQ2),
	.S1(RQ1),
	.S0(RQ0),
	.W0(SYNTHESIZED_WIRE_24),
	.W1(SYNTHESIZED_WIRE_25),
	.W2(SYNTHESIZED_WIRE_26),
	.W3(SYNTHESIZED_WIRE_27),
	.W4(SYNTHESIZED_WIRE_28),
	.W5(SYNTHESIZED_WIRE_29),
	.W6(SYNTHESIZED_WIRE_30),
	.W7(SYNTHESIZED_WIRE_31)
	);


endmodule
