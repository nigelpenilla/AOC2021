lines = [] 

with open('day1-input.txt', 'r') as f:
	lines = f.readlines()

count = 0
i = 0
while i < len(lines) - 3:
	sum1 = int(lines[i]) + int(lines[i + 1]) + int(lines[i + 2])
	sum2 = int(lines[i + 1]) + int(lines[i + 2]) + int(lines[i + 3])
	sumTrue = sum2 > sum1
	if sumTrue: 
		count += 1
	i += 1

print(count)	