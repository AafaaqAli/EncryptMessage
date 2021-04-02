class MessageEncryption{
    companion object {
     	fun encrypt(val message: String, val secretKey: String): String? {
                Security.addProvider(BouncyCastleProvider())
                var keyBytes: ByteArray

                try {
                    keyBytes = secretKey.toByteArray(charset("UTF8"))
                    val skey = SecretKeySpec(keyBytes, "AES")
                    val input = strToEncrypt.toByteArray(charset("UTF8"))

                    synchronized(Cipher::class.java) {
                        val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding")
                        cipher.init(Cipher.ENCRYPT_MODE, skey)

                        val cipherText = ByteArray(cipher.getOutputSize(input.size))
                        var ctLength = cipher.update(
                            input, 0, input.size,
                            cipherText, 0
                        )
                        ctLength += cipher.doFinal(cipherText, ctLength)
                        return String(
                            Base64.encode(cipherText)
                        )
                    }
                } catch (uee: UnsupportedEncodingException) {
                    uee.printStackTrace()
                } catch (ibse: IllegalBlockSizeException) {
                    ibse.printStackTrace()
                } catch (bpe: BadPaddingException) {
                    bpe.printStackTrace()
                } catch (ike: InvalidKeyException) {
                    ike.printStackTrace()
                } catch (nspe: NoSuchPaddingException) {
                    nspe.printStackTrace()
                } catch (nsae: NoSuchAlgorithmException) {
                    nsae.printStackTrace()
                } catch (e: ShortBufferException) {
                    e.printStackTrace()
                }

                return null
            }
        }   
        
        fun decypt(val message: String, val secretKey: String){
            : String? {
    			Security.addProvider(BouncyCastleProvider())
   				 var keyBytes: ByteArray

                try {
                    keyBytes = secretKey.toByteArray(charset("UTF8"))
                    val skey = SecretKeySpec(keyBytes, "AES")
                    val input = org.bouncycastle.util.encoders.Base64
                        .decode(strToDecrypt?.trim { it <= ' ' }?.toByteArray(charset("UTF8")))

                    synchronized(Cipher::class.java) {
                        val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding")
                        cipher.init(Cipher.DECRYPT_MODE, skey)

                        val plainText = ByteArray(cipher.getOutputSize(input.size))
                        var ptLength = cipher.update(input, 0, input.size, plainText, 0)
                        ptLength += cipher.doFinal(plainText, ptLength)
                        val decryptedString = String(plainText)
                        return decryptedString.trim { it <= ' ' }
                    }
                } catch (uee: UnsupportedEncodingException) {
                    uee.printStackTrace()
                } catch (ibse: IllegalBlockSizeException) {
                    ibse.printStackTrace()
                } catch (bpe: BadPaddingException) {
                    bpe.printStackTrace()
                } catch (ike: InvalidKeyException) {
                    ike.printStackTrace()
                } catch (nspe: NoSuchPaddingException) {
                    nspe.printStackTrace()
                } catch (nsae: NoSuchAlgorithmException) {
                    nsae.printStackTrace()
                } catch (e: ShortBufferException) {
                    e.printStackTrace()
                }

                return null
            }
        }
    }
}