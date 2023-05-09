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
// CREATED		"Sat Nov 21 20:36:24 2020"

module register(
	Clock,
	Load,
	In,
	CLRN,
	Out
);


input wire	Clock;
input wire	Load;
input wire	In;
input wire	CLRN;
output wire	Out;

wire	SYNTHESIZED_WIRE_0;
reg	DFF_inst;

assign	Out = DFF_inst;




always@(posedge Clock or negedge CLRN)
begin
if (!CLRN)
	begin
	DFF_inst <= 0;
	end
else
	begin
	DFF_inst <= SYNTHESIZED_WIRE_0;
	end
end


\21mux 	b2v_inst2(
	.S(Load),
	.B(In),
	.A(DFF_inst),
	.Y(SYNTHESIZED_WIRE_0));


endmodule
