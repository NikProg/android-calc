package calc.calc;

/**
 * Created by Nik on 30.04.2016.
 */
public enum InputChar {
    ZERO {
        @Override
        public char toChar() {
            return '0';
        }
    },
    ONE {
        @Override
        public char toChar() {
            return '1';
        }
    },
    TWO {
        @Override
        public char toChar() {
            return '2';
        }
    },
    THREE {
        @Override
        public char toChar() {
            return '3';
        }
    },
    FOUR {
        @Override
        public char toChar() {
            return '4';
        }
    },
    FIVE {
        @Override
        public char toChar() {
            return '5';
        }
    },
    SIX {
        @Override
        public char toChar() {
            return '6';
        }
    },
    SEVEN {
        @Override
        public char toChar() {
            return '7';
        }
    },
    EIGHT {
        @Override
        public char toChar() {
            return '8';
        }
    },
    NINE {
        @Override
        public char toChar() {
            return '9';
        }
    },
    DOT {
        @Override
        public char toChar() {
            return '.';
        }
    };
    public abstract char toChar();
}
