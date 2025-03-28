package com.example.mycalender.model

object QuoteUtils {
    fun getQuoteForDate(day: String, monthAbbr: String, isSunday: Boolean,isSaturday : Boolean, isHoliday: Boolean): String {
        // Return appropriate quote based on date
        return when {
            isHoliday && "$day $monthAbbr" == "06 Jan" -> "The greatest comforts and lasting peace are obtained when one eradicates selfishness from within. – Guru Gobind Singh Ji"
            isHoliday && "$day $monthAbbr" == "26 Jan" -> "Let us remember the golden heritage of our country and feel proud to be a part of India. – Happy Republic Day"
            isHoliday && "$day $monthAbbr" == "12 Feb" -> "Walk on the path of equality and brotherhood. – Guru Ravidas Jayanti"
            isHoliday && "$day $monthAbbr" == "26 Feb" -> "May Lord Shiva bless you with joy, peace, and harmony. – Happy Maha Shivratri"
            isHoliday && "$day $monthAbbr" == "14 Mar" -> "Celebrate colors and love. – Happy Holi"
            isHoliday && "$day $monthAbbr" == "23 Mar" -> "Inquilab Zindabad! – Remembering Bhagat Singh, Sukhdev, Rajguru"
            isHoliday && "$day $monthAbbr" == "31 Mar" -> "May this Eid bring peace and joy. – Eid-ul-Fitr Mubarak"
            isHoliday && "$day $monthAbbr" == "06 Apr" -> "On this holy occasion of Ram Navami, may Lord Ram bless you with health and happiness."
            isHoliday && "$day $monthAbbr" == "08 Apr" -> "Spreading the teachings of Guru Nabha Dass Ji. – Happy Jayanti"
            isHoliday && "$day $monthAbbr" == "10 Apr" -> "Live and let live. – Mahavir Jayanti"
            isHoliday && "$day $monthAbbr" == "13 Apr" -> "Let’s celebrate the spirit of harvest. – Happy Vaisakhi"
            isHoliday && "$day $monthAbbr" == "14 Apr" -> "Cultivation of mind should be the ultimate aim of human existence. – Dr. B.R. Ambedkar"
            isHoliday && "$day $monthAbbr" == "18 Apr" -> "May the spirit of Good Friday stay in our hearts and light our way."
            isHoliday && "$day $monthAbbr" == "29 Apr" -> "Blessings of Lord Parshuram Ji guide you towards success."
            isHoliday && "$day $monthAbbr" == "01 May" -> "Saluting all the hardworking souls. – Happy Labour Day"
            isHoliday && "$day $monthAbbr" == "30 May" -> "Remembering the sacrifice of Guru Arjun Dev Ji. – Martyrdom Day"
            isHoliday && "$day $monthAbbr" == "07 Jun" -> "Eid Mubarak! – May your Bakrid be filled with peace and joy."
            isHoliday && "$day $monthAbbr" == "11 Jun" -> "He who recognizes humanity is the one closest to God. – Kabir Jayanti"
            isHoliday && "$day $monthAbbr" == "15 Aug" -> "Freedom is never dear at any price. – Happy Independence Day"
            isHoliday && "$day $monthAbbr" == "16 Aug" -> "Celebrate the birth of Lord Krishna. – Happy Janmashtami"
            isHoliday && "$day $monthAbbr" == "22 Sep" -> "Following the path of righteousness. – Maharaj Agrasen Jayanti"
            isHoliday && "$day $monthAbbr" == "02 Oct" -> "Be the change you wish to see in the world. – Gandhi Jayanti"
            isHoliday && "$day $monthAbbr" == "07 Oct" -> "Spreading the teachings of Maharishi Valmiki Ji."
            isHoliday && "$day $monthAbbr" == "20 Oct" -> "May the festival of lights brighten your life. – Happy Diwali"
            isHoliday && "$day $monthAbbr" == "22 Oct" -> "Let’s celebrate the architect of the divine. – Vishwakarma Puja"
            isHoliday && "$day $monthAbbr" == "05 Nov" -> "May Guru Nanak Dev Ji inspire you to achieve all your goals. – Happy Gurpurab"
            isHoliday && "$day $monthAbbr" == "16 Nov" -> "Remembering the martyrdom of Kartar Singh Sarabha Ji."
            isHoliday && "$day $monthAbbr" == "25 Nov" -> "Salute to Guru Teg Bahadur Ji’s sacrifice for religious freedom."
            isHoliday && "$day $monthAbbr" == "25 Dec" -> "Peace on Earth and goodwill to all. – Merry Christmas"
            isHoliday && "$day $monthAbbr" == "27 Dec" -> "Shaheedi Sabha – Paying tribute at Fatehgarh Sahib."

            isSunday -> "Today is Sunday, let’s make it a fun day!"
            isSaturday -> "Sat Plan for Funday"
            else -> "just normal day in your daily routine!"
        }
    }
}
