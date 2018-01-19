/*
        ENAMEL PROJECT - MELANIE BALJKO
        YORK UNIVERSITY
        
        CREATED BY:
        SHWETA DIXIT - 2017
        
        TITLE --- UNICODE BRAILLE ALPHABET CALL MODULE
        
        ****************************
        
        (Measurements in mm)
        
        
*/

function str2vec(str, v=[], i=0) =
	i == len(str) ? v :
	str2vec(str, concat(v, str[i]), i+1);

// General unicode braille alphabet module 
module braille_alphabet(height, size, alpha = " ")

{
    
    // Alphabet A
    if (alpha == "A")
    {
        linear_extrude(height)
        text("\u2801", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet B
    if (alpha == "B")
    {
        linear_extrude(height)
        text("\u2803", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet C
    if (alpha == "C")
    {
        linear_extrude(height)
        text("\u2809", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet D
    if (alpha == "D")
    {
        linear_extrude(height)
        text("\u2819", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet E
    if (alpha == "E")
    {
        linear_extrude(height)
        text("\u2811", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet F
    if (alpha == "F")
    {
        linear_extrude(height)
        text("\u280B", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet G
    if (alpha == "G")
    {
        linear_extrude(height)
        text("\u281B", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet H
    if (alpha == "H")
    {
        linear_extrude(height)
        text("\u2813", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet I
    if (alpha == "I")
    {
        linear_extrude(height)
        text("\u280A", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet J
    if (alpha == "J")
    {
        linear_extrude(height)
        text("\u281A", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet K
    if (alpha == "K")
    {
        linear_extrude(height)
        text("\u2805", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet L
    if (alpha == "L")
    {
        linear_extrude(height)
        text("\u2807", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet M
    if (alpha == "M")
    {
        linear_extrude(height)
        text("\u280D", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet N
    if (alpha == "N")
    {
        linear_extrude(height)
        text("\u281D", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet O
    if (alpha == "O")
    {
        linear_extrude(height)
        text("\u2815", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet P
    if (alpha == "P")
    {
        linear_extrude(height)
        text("\u280F", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet Q
    if (alpha == "Q")
    {
        linear_extrude(height)
        text("\u281F", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet R
    if (alpha == "R")
    {
        linear_extrude(height)
        text("\u2817", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet S
    if (alpha == "S")
    {
        linear_extrude(height)
        text("\u280E", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet T
    if (alpha == "T")
    {
        linear_extrude(height)
        text("\u281E", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet U
    if (alpha == "U")
    {
        linear_extrude(height)
        text("\u2825", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet V
    if (alpha == "V")
    {
        linear_extrude(height)
        text("\u2827", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet W
    if (alpha == "W")
    {
        linear_extrude(height)
        text("\u283A", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet X
    if (alpha == "X")
    {
        linear_extrude(height)
        text("\u282D", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet Y
    if (alpha == "Y")
    {
        linear_extrude(height)
        text("\u283D", font = "Segoe UI Symbol", size = size);
    }
    
    // Alphabet Z
    if (alpha == "Z")
    {
        linear_extrude(height)
        text("\u2835", font = "Segoe UI Symbol", size = size);
    }
    
    echo(alpha);
}



// ISO STANDARD UNICODE BRAILLE ALPHABET CALL MODULE - ISO Standard height = 0.9 mm
module iso_std_braille_alpha(alphabet = " ", size)
{
    vecChar = str2vec(alphabet);
    x = 0;
    y = 0;
    z = 0;
    
  
    for (alpha = [0:1:len(vecChar)-1])
        {
            x = alpha * 10;
            //echo(alpha);
            translate([x,y,z])
            {
                braille_alphabet(0.9, size, vecChar[alpha]);
            }
               
            echo(x);
            
            
        }
}



iso_std_braille_alpha("HELLO HOW ARE YOU", 10);
