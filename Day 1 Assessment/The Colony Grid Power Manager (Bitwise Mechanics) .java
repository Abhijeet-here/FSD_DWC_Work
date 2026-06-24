public class ColonyPowerManager {

    // A single byte (8 bits) holding the ON/OFF status of all 8 sectors.
    // Initializes to 00000000 (all sectors OFF).
    private byte sectorStates = 0;

    /**
     * Turns ON a specific sector using Bitwise OR (|) and Left Shift (<<).
     * * @param sectorIndex The sector to turn on (0 to 7)
     */
    public void turnOnSector(int sectorIndex) {
        // 1. Shift '1' to the left by 'sectorIndex' positions to create a mask.
        // 2. Bitwise OR (|) applies the mask, forcing the target bit to 1 while leaving others intact.
        sectorStates = (byte) (sectorStates | (1 << sectorIndex));
    }

    /**
     * Turns OFF a specific sector using Bitwise AND (&), NOT (~), and Left Shift (<<).
     * * @param sectorIndex The sector to turn off (0 to 7)
     */
    public void turnOffSector(int sectorIndex) {
        // 1. Shift '1' left by 'sectorIndex'.
        // 2. Bitwise NOT (~) flips the bits, so the target bit is 0 and all others are 1.
        // 3. Bitwise AND (&) applies the mask, forcing the target bit to 0 while leaving others intact.
        sectorStates = (byte) (sectorStates & ~(1 << sectorIndex));
    }

    /**
     * Checks if a specific sector is ON using Bitwise AND (&) and Left Shift (<<).
     * * @param sectorIndex The sector to check (0 to 7)
     * @return true if the sector is ON, false otherwise
     */
    public boolean isSectorOn(int sectorIndex) {
        // 1. Shift '1' left by 'sectorIndex' to create the mask.
        // 2. Bitwise AND (&) isolates the target bit.
        // 3. If the result is not exactly 0, it means the bit at that position was 1.
        return (sectorStates & (1 << sectorIndex)) != 0;
    }

    // --- Demonstration / Testing ---
    public static void main(String[] args) {
        ColonyPowerManager node = new ColonyPowerManager();

        System.out.println("Initial Boot State: " + getBinaryString(node.sectorStates));

        // Turn on Sector 0 (Life Support) and Sector 3 (Communications)
        node.turnOnSector(0); 
        node.turnOnSector(3);
        System.out.println("After turning on sectors 0 and 3: " + getBinaryString(node.sectorStates));

        // Check statuses
        System.out.println("Is Sector 3 ON? " + node.isSectorOn(3));
        System.out.println("Is Sector 1 ON? " + node.isSectorOn(1));

        // Turn off Sector 3
        node.turnOffSector(3);
        System.out.println("After turning off sector 3: " + getBinaryString(node.sectorStates));
    }

    /**
     * Helper method to visualize the byte as an 8-bit string.
     * (Bitwise AND with 0xFF prevents sign-extension issues when printing).
     */
    private static String getBinaryString(byte b) {
        return String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
    }
}