from Cryptodome.Cipher import AES
#Cryto library used is Cryptodome for Python

words = open("words.txt").read().splitlines()
#words.txt is text file supplied

plaintext = b'This is a top secret.###########'

ivhex = 'aabbccddeeff00998877665544332211'
ivbytes = bytes.fromhex(ivhex)

for w in words:
	wpad = w + '#'*(16-(len(w)%16))
	wbytes = wpad.encode()
	
	cipher = AES.new(wbytes, AES.MODE_CBC, ivbytes)
	answer = cipher.decrypt(bytes.fromhex('764aa26b55a4da654df6b19e4bce00f4ed05e09346fb0e762583cb7da2ac93a2'))	
	try:
		if 'This is a top secret.' in answer.decode():
			print('The code is: ' + answer.decode())
			print('The key is: ' + w)
	except:
		correct = False
		
''' Generated Text:
	The code is: This is a top secret.
	The key is: Syracuse
'''