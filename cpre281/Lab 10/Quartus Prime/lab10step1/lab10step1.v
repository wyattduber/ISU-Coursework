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
// CREATED		"Thu Nov 12 11:50:50 2020"

module lab10step1(
	In,
	Clock,
	Q1,
	Q2,
	Q3,
	Q4
);


input wire	In;
input wire	Clock;
output wire	Q1;
output wire	Q2;
output wire	Q3;
output reg	Q4;

wire	SYNTHESIZED_WIRE_0;
wire	SYNTHESIZED_WIRE_1;
wire	SYNTHESIZED_WIRE_2;
reg	DFF_inst;
wire	SYNTHESIZED_WIRE_3;
wire	SYNTHESIZED_WIRE_4;
reg	DFF_inst1;
wire	SYNTHESIZED_WIRE_5;
wire	SYNTHESIZED_WIRE_6;
reg	DFF_inst2;
wire	SYNTHESIZED_WIRE_7;

assign	Q1 = DFF_inst;
assign	Q2 = DFF_inst1;
assign	Q3 = DFF_inst2;
assign	SYNTHESIZED_WIRE_0 = 1;
assign	SYNTHESIZED_WIRE_1 = 1;
assign	SYNTHESIZED_WIRE_2 = 1;
assign	SYNTHESIZED_WIRE_3 = 1;
assign	SYNTHESIZED_WIRE_4 = 1;
assign	SYNTHESIZED_WIRE_5 = 1;
assign	SYNTHESIZED_WIRE_6 = 1;
assign	SYNTHESIZED_WIRE_7 = 1;




always@(posedge Clock or negedge SYNTHESIZED_WIRE_0 or negedge SYNTHESIZED_WIRE_1)
begin
if (!SYNTHESIZED_WIRE_0)
	begin
	DFF_inst <= 0;
	end
else
if (!SYNTHESIZED_WIRE_1)
	begin
	DFF_inst <= 1;
	end
else
	begin
	DFF_inst <= In;
	end
end


always@(posedge Clock or negedge SYNTHESIZED_WIRE_2 or negedge SYNTHESIZED_WIRE_3)
begin
if (!SYNTHESIZED_WIRE_2)
	begin
	DFF_inst1 <= 0;
	end
else
if (!SYNTHESIZED_WIRE_3)
	begin
	DFF_inst1 <= 1;
	end
else
	begin
	DFF_inst1 <= DFF_inst;
	end
end




always@(posedge Clock or negedge SYNTHESIZED_WIRE_4 or negedge SYNTHESIZED_WIRE_5)
begin
if (!SYNTHESIZED_WIRE_4)
	begin
	DFF_inst2 <= 0;
	end
else
if (!SYNTHESIZED_WIRE_5)
	begin
	DFF_inst2 <= 1;
	end
else
	begin
	DFF_inst2 <= DFF_inst1;
	end
end


always@(posedge Clock or negedge SYNTHESIZED_WIRE_6 or negedge SYNTHESIZED_WIRE_7)
begin
if (!SYNTHESIZED_WIRE_6)
	begin
	Q4 <= 0;
	end
else
if (!SYNTHESIZED_WIRE_7)
	begin
	Q4 <= 1;
	end
else
	begin
	Q4 <= DFF_inst2;
	end
end








endmodule
