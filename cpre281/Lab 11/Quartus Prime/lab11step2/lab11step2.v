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
// CREATED		"Fri Nov 20 10:53:11 2020"

module lab11step2(
	PRN,
	w,
	Clock,
	CLRN,
	pin_name1,
	pin_name2,
	pin_name3
);


input wire	PRN;
input wire	w;
input wire	Clock;
input wire	CLRN;
output wire	pin_name1;
output wire	pin_name2;
output wire	pin_name3;

reg	SYNTHESIZED_WIRE_6;
wire	SYNTHESIZED_WIRE_0;
wire	SYNTHESIZED_WIRE_1;
reg	DFF_inst;
reg	SYNTHESIZED_WIRE_7;
wire	SYNTHESIZED_WIRE_2;
wire	SYNTHESIZED_WIRE_3;
wire	SYNTHESIZED_WIRE_4;
wire	SYNTHESIZED_WIRE_5;

assign	pin_name1 = SYNTHESIZED_WIRE_6;
assign	pin_name2 = SYNTHESIZED_WIRE_7;
assign	pin_name3 = DFF_inst;




always@(posedge Clock or negedge CLRN or negedge PRN)
begin
if (!CLRN)
	begin
	DFF_inst <= 0;
	end
else
if (!PRN)
	begin
	DFF_inst <= 1;
	end
else
	begin
	DFF_inst <= SYNTHESIZED_WIRE_6;
	end
end


always@(posedge Clock or negedge CLRN or negedge PRN)
begin
if (!CLRN)
	begin
	SYNTHESIZED_WIRE_6 <= 0;
	end
else
if (!PRN)
	begin
	SYNTHESIZED_WIRE_6 <= 1;
	end
else
	begin
	SYNTHESIZED_WIRE_6 <= SYNTHESIZED_WIRE_0;
	end
end


always@(posedge Clock or negedge CLRN or negedge PRN)
begin
if (!CLRN)
	begin
	SYNTHESIZED_WIRE_7 <= 0;
	end
else
if (!PRN)
	begin
	SYNTHESIZED_WIRE_7 <= 1;
	end
else
	begin
	SYNTHESIZED_WIRE_7 <= SYNTHESIZED_WIRE_1;
	end
end

assign	SYNTHESIZED_WIRE_3 = w & DFF_inst & SYNTHESIZED_WIRE_7 & SYNTHESIZED_WIRE_6;

assign	SYNTHESIZED_WIRE_1 = SYNTHESIZED_WIRE_2 | SYNTHESIZED_WIRE_3;

assign	SYNTHESIZED_WIRE_2 = SYNTHESIZED_WIRE_7 & SYNTHESIZED_WIRE_4;

assign	SYNTHESIZED_WIRE_4 =  ~w;

assign	SYNTHESIZED_WIRE_5 = SYNTHESIZED_WIRE_7 & w;

assign	SYNTHESIZED_WIRE_0 = SYNTHESIZED_WIRE_5 | SYNTHESIZED_WIRE_6;


endmodule
