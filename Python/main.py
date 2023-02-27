import codecs

with codecs.open('TestFile.txt', 'r', encoding='utf-8', errors='ignore') as f:
    contenido = f.read()
    print(contenido)
