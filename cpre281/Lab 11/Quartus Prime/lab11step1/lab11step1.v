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
// CREATED		"Fri Nov 20 10:41:55 2020"

module lab11step1(
	PRN,
	Clock,
	CLRN,
	w,
	O1,
	O2,
	O3
);


input wire	PRN;
input wire	Clock;
input wire	CLRN;
input wire	w;
output wire	O1;
output wire	O2;
output wire	O3;

reg	DFF_inst;
wire	SYNTHESIZED_WIRE_0;
wire	SYNTHESIZED_WIRE_1;
reg	DFF_inst2;
reg	DFF_inst1;

assign	O1 = DFF_inst;
assign	O2 = DFF_inst1;
assign	O3 = DFF_inst2;




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
	DFF_inst <= DFF_inst;
	end
end


always@(posedge Clock or negedge CLRN or negedge PRN)
begin
if (!CLRN)
	begin
	DFF_inst1 <= 0;
	end
else
if (!PRN)
	begin
	DFF_inst1 <= 1;
	end
else
	begin
	DFF_inst1 <= SYNTHESIZED_WIRE_0;
	end
end


always@(posedge Clock or negedge CLRN or negedge PRN)
begin
if (!CLRN)
	begin
	DFF_inst2 <= 0;
	end
else
if (!PRN)
	begin
	DFF_inst2 <= 1;
	end
else
	begin
	DFF_inst2 <= SYNTHESIZED_WIRE_1;
	end
end

assign	SYNTHESIZED_WIRE_1 =  ~DFF_inst2;

assign	SYNTHESIZED_WIRE_0 =  ~DFF_inst1;


endmodule
