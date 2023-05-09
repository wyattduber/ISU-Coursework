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
// CREATED		"Sat Nov 21 20:47:12 2020"

module reg4b(
	LOAD,
	CLK,
	CLRN,
	IN,
	Out
);


input wire	LOAD;
input wire	CLK;
input wire	CLRN;
input wire	[3:0] IN;
output wire	[3:0] Out;

wire	[3:0] OUT_ALTERA_SYNTHESIZED;





register	b2v_inst(
	.In(IN[2]),
	.Load(LOAD),
	.Clock(CLK),
	.CLRN(CLRN),
	.Out(OUT_ALTERA_SYNTHESIZED[2]));


register	b2v_inst2(
	.In(IN[3]),
	.Load(LOAD),
	.Clock(CLK),
	.CLRN(CLRN),
	.Out(OUT_ALTERA_SYNTHESIZED[3]));


register	b2v_inst3(
	.In(IN[1]),
	.Load(LOAD),
	.Clock(CLK),
	.CLRN(CLRN),
	.Out(OUT_ALTERA_SYNTHESIZED[1]));


register	b2v_inst4(
	.In(IN[0]),
	.Load(LOAD),
	.Clock(CLK),
	.CLRN(CLRN),
	.Out(OUT_ALTERA_SYNTHESIZED[0]));

assign	Out = OUT_ALTERA_SYNTHESIZED;

endmodule
