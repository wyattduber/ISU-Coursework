module lab3step2(F,C,G,W,A);

input F,C,G,W;
output A;

assign A = ~(F & (C | G)) ^ ~(F & (W | G));

endmodule