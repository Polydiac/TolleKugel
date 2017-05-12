import socket

HOST = ''
PORT = 1010

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((HOST, PORT))
s.listen(1)
conn, addr = s.accept()
print('Connected by' + addr)
while 1:
    date = conn.recv(1024)
    if not data
