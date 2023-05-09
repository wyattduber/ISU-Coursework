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
// CREATED		"Wed Nov 04 17:06:04 2020"

module lab9step3a(
	D,
	Clk,
	Q,
	Qn
);


input wire	D;
input wire	Clk;
output wire	Q;
output wire	Qn;

wire	SYNTHESIZED_WIRE_0;
wire	SYNTHESIZED_WIRE_1;





lab9step2	b2v_inst(
	.D(D),
	.Clk(Clk),
	.Q(SYNTHESIZED_WIRE_0)
	);


lab9step2	b2v_inst1(
	.D(SYNTHESIZED_WIRE_0),
	.Clk(SYNTHESIZED_WIRE_1),
	.Q(Q),
	.Qn(Qn));

assign	SYNTHESIZED_WIRE_1 =  ~Clk;


endmodule
