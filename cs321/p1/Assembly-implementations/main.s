// This is main.c compiled on a Raspberry Pi 4 into ARMv6, which should have a lot of similarities to LEGv8

	.arch armv6
	.eabi_attribute 28, 1
	.eabi_attribute 20, 1
	.eabi_attribute 21, 1
	.eabi_attribute 23, 3
	.eabi_attribute 24, 1
	.eabi_attribute 25, 1
	.eabi_attribute 26, 2
	.eabi_attribute 30, 2
	.eabi_attribute 34, 1
	.eabi_attribute 18, 4
	.file	"main.c"
	.text
	.align	2
	.global	swapArr
	.arch armv6
	.syntax unified
	.arm
	.fpu vfp
	.type	swapArr, %function
swapArr:
	@ args = 0, pretend = 0, frame = 0
	@ frame_needed = 0, uses_anonymous_args = 0
	@ link register save eliminated.
	ldr	r3, [r0, r1, lsl #2]
	ldr	ip, [r0, r2, lsl #2]
	str	ip, [r0, r1, lsl #2]
	str	r3, [r0, r2, lsl #2]
	bx	lr
	.size	swapArr, .-swapArr
	.align	2
	.global	findSmallest
	.syntax unified
	.arm
	.fpu vfp
	.type	findSmallest, %function
findSmallest:
	@ args = 0, pretend = 0, frame = 0
	@ frame_needed = 0, uses_anonymous_args = 0
	cmp	r2, r1
	str	lr, [sp, #-4]!
	add	lr, r0, r2, lsl #2
	ldr	r3, [r0, r2, lsl #2]
	mov	r0, r2
	ldrge	pc, [sp], #4
.L5:
	add	r2, r2, #1
	cmp	r1, r2
	ldreq	pc, [sp], #4
	ldr	ip, [lr, #4]!
	cmp	r3, ip
	movgt	r0, r2
	movgt	r3, ip
	b	.L5
	.size	findSmallest, .-findSmallest
	.align	2
	.global	selectionSort
	.syntax unified
	.arm
	.fpu vfp
	.type	selectionSort, %function
selectionSort:
	@ args = 0, pretend = 0, frame = 0
	@ frame_needed = 0, uses_anonymous_args = 0
	cmp	r1, #0
	bxle	lr
	push	{r4, r5, r6, r7, r8, lr}
	sub	r5, r0, #4
	add	r8, r5, r1, lsl #2
	mov	r6, #0
.L17:
	ldr	r7, [r5, #4]!
	mov	r3, r6
	mov	r4, r5
	mov	r2, r7
	mov	lr, r6
	b	.L13
.L25:
	ldr	ip, [r4, #4]!
	cmp	r2, ip
	movgt	lr, r3
	movgt	r2, ip
.L13:
	add	r3, r3, #1
	cmp	r1, r3
	bgt	.L25
	ldr	r3, [r0, lr, lsl #2]
	cmp	r5, r8
	str	r7, [r0, lr, lsl #2]
	add	r6, r6, #1
	str	r3, [r5]
	bne	.L17
	pop	{r4, r5, r6, r7, r8, pc}
	.size	selectionSort, .-selectionSort
	.align	2
	.global	fillRandom
	.syntax unified
	.arm
	.fpu vfp
	.type	fillRandom, %function
fillRandom:
	@ args = 0, pretend = 0, frame = 0
	@ frame_needed = 0, uses_anonymous_args = 0
	push	{r4, r5, r6, r7, r8, lr}
	mov	r5, r0
	mov	r0, #0
	mov	r7, r1
	bl	time
	bl	srand
	cmp	r7, #0
	ble	.L27
	sub	r4, r5, #4
	add	r6, r4, r7, lsl #2
.L28:
	bl	rand
	str	r0, [r4, #4]!
	cmp	r4, r6
	bne	.L28
.L27:
	sub	ip, r7, #1
	cmp	ip, #0
	ble	.L29
	sub	r0, r5, #4
	add	r0, r0, r7, lsl #2
.L30:
	cmp	ip, #0
	movgt	r3, r5
	ble	.L33
.L32:
	ldr	r2, [r3]
	ldr	r1, [r3, #4]!
	cmp	r2, r1
	stmdalt	r3, {r1, r2}
	cmp	r3, r0
	bne	.L32
.L33:
	sub	r0, r0, #4
	cmp	r5, r0
	sub	ip, ip, #1
	bne	.L30
.L29:
	mov	r0, r5
	pop	{r4, r5, r6, r7, r8, pc}
	.size	fillRandom, .-fillRandom
	.section	.text.startup,"ax",%progbits
	.align	2
	.global	main
	.syntax unified
	.arm
	.fpu vfp
	.type	main, %function
main:
	@ args = 0, pretend = 0, frame = 400
	@ frame_needed = 0, uses_anonymous_args = 0
	push	{r4, r5, r6, lr}
	sub	sp, sp, #400
	mov	r1, #100
	mov	r0, sp
	bl	fillRandom
	mov	r0, sp
	mov	r1, #100
	bl	selectionSort
	ldr	r6, .L42
	sub	r4, sp, #4
	add	r5, sp, #396
.L39:
	ldr	r1, [r4, #4]!
	mov	r0, r6
	bl	printf
	cmp	r4, r5
	bne	.L39
	mov	r0, #10
	bl	putchar
	mov	r0, #0
	add	sp, sp, #400
	@ sp needed
	pop	{r4, r5, r6, pc}
.L43:
	.align	2
.L42:
	.word	.LC0
	.size	main, .-main
	.section	.rodata.str1.4,"aMS",%progbits,1
	.align	2
.LC0:
	.ascii	"%d, \000"
	.ident	"GCC: (Raspbian 8.3.0-6+rpi1) 8.3.0"
	.section	.note.GNU-stack,"",%progbits
