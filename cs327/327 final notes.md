Final Topics:

1. Everything in C

2. Compiling and Setting up c++ programs
	a. Header files (what is in them/why)
		Starts with 
			\#ifndef CELLULARAUTOMATON_H_ //Example from project2b used
			\#define CELLULARAUTOMATON_H_ //Example from project2b used
		Ends with
			\#endif
			
		**Example Class**
		
		\#ifndef MYCLASS_H_
		\#define MYCLASS_H_
		\#include <string>
		
		using namespace std;
		class myClass {
			public:
			void doRandomThing(int thing, std::string otherThing);
			std::string returnThing(int thing);
		};
		
		\#endif
		
	b. include makefiles
	
3. cin, cout, and cerr
	cin >> x; //Example of input
	cout << "Hello world!\n" << endl; // Example of output with both kinds of newline
	cerr << "Error: " << var_name << " greater than expected amount!" << endl; // example of error output with variable
	\#include <iostream> // Library for cin/cout/cerr

4. c++ string class
	a. functions, features, uses, etc
		getline
			string str;
			getline(cin, str); //Example of how to input a line into a string variable
		str.push_back('s'); //Example inserts 's' character at the end of the string
		str.pop_back(); //Example deletes the last character in a string, in this case the 's' that we just added
		str.resize(12); //Example resizes the string from 0 to $input
		str.length(); //Example returns the length of the string
		str.shrink_to_fit(); //Example decreases the capacity of the string 
		str.capacity(); //Example the total capacity of the string size in memory
		
	
5. c++ references (different than c)
	a. all the stuff including targets from function returns.
	
		int main() {
			int i = 17;
			int& r = i;
			
			cout << "Number: " << i << endl;
			cout << "Number reference: " << r << endl;
			
			Both will print out 17;

6. Access Modifiers on variables and functions	
	a. private, protected, public, etc.
	a. public: all of the class members will be available to everyone (all other classes and API's)
	b. private: class members can only be referenced by other member functions inside the class, not anyone else or outside
	c. protected: similar to private, except that it can be accessed outside of it's class with the help of a friend class.
		Can also be accessed by any subclass (derived class) of that class as well.
	
7. Inlining - what does it do and why do it
	a. What it does	
		1. Function call overhead doesn't occur
		2. saves the overhead of push/pop variables on the stack when the function is called
		3. saves overhead of a return call from a function.
		4. Compiler can perform contect specific optimization on the body of a function
		5. may be usefull for smaller (embedded) systems as it can yeld less code than the function call preamble and return.
	b. Why do it 
		1. If you bave a lot of small functions with a few lines of code, it can take much more time to make the function call than 
		   it does to execute the actual code within it. inlining removes that overhead and makes it much faster to call smaller functions.
		2. Again, can be useful for embedded systems since embedded systems usually have less memory to use and would therefore benefit
		   from a smaller function call overhead.

8. const in c++
	a. cannot be left un-initialized at the time of assignment
	b. Cann ot be assigned a value anywhere in the program
	c. Explicit value needed to be provided to the constant variable at the time of declaration of the constant variable.
	d. can be called a read-only variable

9. pointers in c++ (mostly the same as c, except for object pointers) (KNOW THE DIFFERENCE!)

10. Destructor / Copy Constructor / assignment = 
	a. Destructor: used to delete allocated memory, used for when pointers are created and need the memory references removed
		//Example
		
		class String {
			private:
				char\* s;
				int size;
			public:
				String(char*);
				~String();
		};
		
		String::String(char\* c) {
			size = strlen(c);
			s = new char\[size + 1\];
			strcpy(s, c);
		}
		String::~String() { delete\[\] s; }
		
	b. Copy Constructor: initializes an object using another object of the same class.
		General prototype: ClassName(const ClassName &old_obj);
		
		//Example
		
		class Point
		{
		private:
			int x, y;
		public:
			Point(int x1, int y1) { x = x1; y = y1; }

			// Copy constructor
			Point(const Point &p1) {x = p1.x; y = p1.y; }

			int getX()		 { return x; }
			int getY()		 { return y; }
		};

		int main()
		{
			Point p1(10, 15); // Normal constructor is called here
			Point p2 = p1; // Copy constructor is called here

			// Let us access values assigned by constructors
			cout << "p1.x = " << p1.getX() << ", p1.y = " << p1.getY();
			cout << "\np2.x = " << p2.getX() << ", p2.y = " << p2.getY();

			return 0;
		}

	
11. Initializers
	a. can you give an example of an initializer where it would throw an error if you did it in the wrong order?
	
		//Example
		
		class Base
		{
			private:
			int value;
			public:
			// default constructor
			Base(int value):value(value)
			{
				cout << "Value is " << value;
			}
		};

		int main()
		{
			Base il(10);
			return 0;
		}
	
12. new and delete keywords
	a. new: allocates memory to a new (pointer) object
	b. delete: deletes the pointer to an object and frees up the memory
	c. go along with destructor and copy constructor methods
		1. copy constructor -> new 
		2. destructor -> delete
		3. all using pointers and references
	
13. nested classes
	a. subclass within a higher level class and can be used within the higher level class as an object
	
	//Example
	
		/* start of Enclosing class declaration */
		class Enclosing {	
				
			int x;
			
			/* start of Nested class declaration */
			class Nested {
				int y;
			}; // declaration Nested class ends here

			void EnclosingFun(Nested *n) {
					cout<<n->y; // Compiler Error: y is private in Nested
			}	
		}; // declaration Enclosing class ends here

		int main()
		{
			
		}


14. friend attribute/method - and why we need it
	a. if we want two classes and dont want an accessor method, we can create a friend method to link between both classes.
	b. Friends should only be used for limited purposes. too many functions or external classes are declared as friends
	   of a class with protected or private data, it lessens the value of encapsulation of seperate classes in object-oriented programming
	c. Friendship is not mutual. If class A is a friend of B, then B doesn't become a friend of A automatically.
	d. Friendship is not inherited.
	
	//Example of class A having a friend class to access A's methods
	
	class A {
		private:
			int a;
			
		public:
			A() { a = 0; }
			friend class B;
	};
	
	class B {
		private:
			int b;
		
		public:
			void showA(A& x) {
				std::cout << "A::a=" << x.a;
			}
	};
	
	int main() {
		A a;
		B b;
		b.showA(a); //Returns "A::a=0"
		return 0;
	}
	
	//Example of class B having a friend void method with A's showB method
	
	class B;
	
	class A {
		public:
		void showB(B&);
	};
	
	class B {
		private:
			int b;
			
		public:
			B() { b = 0; }
			friend void A::showB(B& x);
	};
	
	void A::showB(B& x) {
		std::cout << "B::b = " << x.b;
	}
	
	int main() {
		A a;
		B b;
		a.showB(x); //Returns "B::b = 0"
		return 0;
	}

15. static functions and variables
	a. When a variable is declared static, space for it gets allocated for the lifetime of the program. 
		1. Even if the function is called multiple times, space for the static variable is allocated only once and the value of variable
		   in the previous call gets carried through the next function call.
		2. This is useful for coroutines or any other application where previous state of function needs to be stored.
		
	//Example
	
	void demo() {
		static int count = 0;
		cout << count << " ";
		count++;
	}
	
	int main() {
		for (int i = 0; i < 5; i++) {
			demo(); //Returns "1 2 3 4 5"
		}
		return 0;
	}

16. c++ inheritance - lots of stuff
	visibility mod, polymorphism, virtual
	
	class Dog : public Pet { }
	
17. slicing
	a. When a derived class object is assigned to a base class object.
	b. the reverse is not possible.
	
	//Example
	
	class Base { int x, y; };

	class Derived : public Base { int z, w; };

	int main()
	{
		Derived d;
		Base b = d; // Object Slicing, z and w of d are sliced off
	}


18. Operator overloading
	a. In c++, we can make operators to work for user defined classes.
	b. This means c++ has the ability to provide the operators with a special meaning for a data type, this ability is known as operator overloading. 
	c. For exampele, we can overload an operator '+' in a class like String so that we can concatenate two strings by just using '+'.
	d. Other example lcasses where arithmetic operators may be overloaded are Complex Number, Fractional Number, Big Integer, etc. 
	e. So to sum it down, this means that the operator 

	//Example

	class Complex {
	private:
		int real, imag;
	public:
		Complex(int r = 0, int i =0)  {real = r;   imag = i;}
		  
		// This is automatically called when '+' is used with
		// between two Complex objects
		Complex operator + (Complex const &obj) {
			 Complex res;
			 res.real = real + obj.real;
			 res.imag = imag + obj.imag;
			 return res;
		}
		void print() { cout << real << " + i" << imag << endl; }
	};
	  
	int main()
	{
		Complex c1(10, 5), c2(2, 4);
		Complex c3 = c1 + c2; // An example call to "operator+"
		c3.print();
	}

19. memory management
	a. pointer readability - root pointers
	b. reference counting
	c. mark and sweep
	d. copying garbage collection

20. templates - bitwise operators
	a. class
	b. function
	c. specialization
	
	1. Used to create functions where you can pass any typevalue to use within the function
	2. Basically the concept used in java of ex: ArrayList<String, Integer> where you can have it expect different types
	
	//Example
	
	template <typename T> T myMax(T x, T y) {
		return (x > y)? x: y;
	}
	
	int main() {
		cout << myMax<int>(3, 7) << endl;
		cout << myMax<double>(3.0, 7.0), << endl;
		cout << myMax<char>('g', 'e') << endl;
	}
	
Virtual Keyword:

class BaseClass {
	public:
		int foo(int) {
			printf("In Foo");
		}
}

class SubClass: public BaseClass {
	public:
		int foo(int x) {
			printf("In Sub");
		}
}

SubClass z();
z.foo(); // prints "In Sub"

BaseClass z2();
z2.foo(); // prints "In Foo"

z2 = z;
z2.foo(); // prints idk man

BaseClass *doj();
doj = new SubClass();
doj->foo(3); // prints "In Foo"