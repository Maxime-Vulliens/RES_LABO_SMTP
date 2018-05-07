
import random


f=open("messagelist.txt","w+")

for i in range(100):
	text=random.sample(1*"abcdefghijklmnopqrstuvwxyz",1)
	for j in range(random.randint(50,100)):
		t = random.randint(5,13)
		text+=random.sample(t*"abcdefghijklmnopqrstuvwxyz",t)
		text+= " "
	
	str1 = ''.join(text)
	f.write(str1 + "\n") 

f.close()