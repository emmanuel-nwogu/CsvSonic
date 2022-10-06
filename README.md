### Problem statement
You work for a company that has certain trade secrets in WAV audio format.
However, we would prefer storing the audio files as lightly encrypted text files that could be easily concealed as 
"dump” files. Using XOR encoding/decoding, implement a function that reads a file as a byte buffer, xors each byte in
this buffer a given number of times using a given key, and stores the resulting buffer as a file using a given output
path.

### Note
Although, the problem statement requires two-way conversion functionality between audio files and text files, 
the functionality contained works for any file formats that can be represented as byte buffers.