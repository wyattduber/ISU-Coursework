module Decoder3to8(EN, W2, W1, W0, Y0, Y1, Y2, Y3, Y4, Y5, Y6, Y7);
   input EN, W2, W1, W0;
   output Y0, Y1, Y2, Y3, Y4, Y5, Y6, Y7;
   reg Y0, Y1, Y2, Y3, Y4, Y5, Y6, Y7;
	
   	always @(EN, W2, W1, W0)
		begin
			
			if (EN)
				begin
					Y0=1'b0;
					Y1=1'b0;
					Y2=1'b0;
					Y3=1'b0;
					Y4=1'b0;
					Y5=1'b0;
					Y6=1'b0;
					Y7=1'b0;
					case ({W2,W1,W0})
						3'b000: Y0=1'b1;
						3'b001: Y1=1'b1;
						3'b010: Y2=1'b1;
						3'b011: Y3=1'b1;
						3'b100: Y4=1'b1;
						3'b101: Y5=1'b1;
						3'b110: Y6=1'b1;
						3'b111: Y7=1'b1;
					endcase
				end
			else
				Y0=1'b0;
				Y1=1'b0;
				Y2=1'b0;
				Y3=1'b0;
				Y4=1'b0;
				Y5=1'b0;
				Y6=1'b0;
				Y7=1'b0;
		end 
endmodule
