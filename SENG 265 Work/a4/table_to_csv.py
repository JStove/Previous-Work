import sys
import HTMLParser

result = None

class TableParser(HTMLParser.HTMLParser):
	def __init__(self):
		HTMLParser.HTMLParser.__init__(self)
		self.in_td = False
		self.table = []
		self.info = ['']
		self.infoc = 0
		self.maxc = 0
		self.infof = 0
		self.style = False
		self.tdyes = False

	def handle_starttag(self, tag, attrs):
		if self.in_td == True:
			self.info[self.infof] += ("<"+tag+">")
			self.style = True	
		if tag == 'td' or tag == 'th':
			self.in_td = True
			self.tdyes = True
			if self.infoc != 0:
				self.info[self.infof] += ","
				self.infoc += 1
			else:
				self.infoc += 1




	def handle_data(self, data):
		if self.in_td:
			self.info[self.infof] += ' '.join(data.strip().split())
			
	def handle_endtag(self, tag):
		self.in_td = False
		if tag == 'tr':
			self.info.append('')
			if self.tdyes == False:
				self.infoc += 1

			if self.maxc < self.infoc:
				self.maxc = self.infoc
			while self.infoc < self.maxc:
				self.info[self.infof] += ','
				self.infoc += 1
			self.infoc = 0
			self.infof += 1
			self.tdyes = False
		
		if tag == 'table':
			self.table.append(self.info)
			self.info = ['']
			self.maxc = 0
			self.infof = 0
			self.tdyes = False

		if self.style == True:
			self.info[self.infof] += ("</"+tag+">")
			self.style = False





html = sys.stdin.read().replace('\r', '').replace('\n', '')

p = TableParser()
p.feed(html)
tablenum = 1
for fdsa in p.table:
	sys.stdout.write("TABLE " + str(tablenum) + ": \n")
	tablenum += 1
	for asdf in fdsa:
		sys.stdout.write(asdf + '\n')
