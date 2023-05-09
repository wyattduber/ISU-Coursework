var rs = require('readline-sync');

//Part 1: Take four integer numbers as input
var num1 = rs.question('First Number: ');
var num2 = rs.question('Second Number: ');
var num3 = rs.question('Third Number: ');
var num4 = rs.question('Fourth Number: ');
var result1 = 1;

//Part 2: Find the Factorial of the First Number
if (num1 !== 0 || num1 !== 1) {
    result1 = num1;
    for (var i = num1 - 1; i >= 1; i--) {
        result1 *= i;
    }
}
result1 = eval(result1);
console.log("Factorial of the 1st Number is = " + result1);

//Part 3: Calculate the sum of all digits of the second number
var num2String = num2.toString();
var total2 = 0;
for (var j = 0; j < num2String.length; j++) {
    total2 += Number(num2String.charAt(j));
}
console.log("Sum of all digits of the 2nd number is = " + total2);

//Part 4: Reverse the 3rd number
var num3String = num3.toString();
var total3 = "";
for (var k = 0; k < num3String.length; k++) {
    total3 += Number(num3String.charAt((num3String.length - 1) - k));
}
console.log("The reverse of the 3rd number is = " + Number(total3));

//Part 5: Check if number is a palindrome
function palindrome(str) {
    let i = 0;
    let j = str.length - 1;
    while(i < j) {
        if(str[i] === str[j]) {
            i++;
            j--;
        }
        else {
            return false;
        }
    }
    return true;
}
if(palindrome(num4.toString())) {
    console.log("Is the 4th number a palindrome (True/False)? = True");
} else {
    console.log("Is the 4th number a palindrome (True/False)? = False");
}