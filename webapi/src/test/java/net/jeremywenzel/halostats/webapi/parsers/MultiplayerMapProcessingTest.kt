package net.jeremywenzel.halostats.webapi.parsers

import net.jeremywenzel.halostats.core.haloapi.MultiplayerMap
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MultiplayerMapProcessingTest: BaseProcessingTest() {

    lateinit var maps : Array<MultiplayerMap>

    @Before
    fun `Parse Multiplayer Response`() {
        val responseParser = MultiplayerMapsResponseParser()
        maps = responseParser.parseResponse(getResponseInputStream("MultiplayerMapRequest.json"))
    }

    @Test
    fun `Verify Maps Not Null`() {
        assertNotNull(maps)
    }

    @Test
    fun `Verify first map`() {
        verifyMap(maps.first(),
                "Urban",
                "Andesia was the crucible for countless heroes and villains caught in the throes of seething rebellion and righteous excess.",
                "https://content.halocdn.com/media/Default/games/halo-5-guardians/map-images/warzone/warzone_maps_array08-b2c5175d0e8446c29e4fc6888492e5ff.jpg",
                "c93d708f-f206-11e4-a815-24be05e24f7e")
    }

    @Test
    fun `Verify last map`() {
        verifyMap(maps.last(),
                null,
                null,
                "https://content.halocdn.com/media/Default/games/halo-5-guardians/map-images/arena/arena_maps_sustain_array05-d6b9b16e1d554e96b1ff594945140f0d.jpg",
                "5875580b-14aa-4cda-bc1f-a42732585364")
    }

    /**
     * Just a quick method to verify that map and the information in it.
     */
    private fun verifyMap(map: MultiplayerMap, expectedName: String?, expectedDesc: String?, expectedUrl: String, expectedGuid: String) {
        if (expectedName == null) {
            assertNull(map.name)
        }
        else {
            assertEquals(expectedName, map.name)
        }

        if (expectedDesc == null) {
            assertNull(map.description)
        }
        else {
            assertEquals(expectedDesc, map.description)
        }
        assertEquals(expectedUrl, map.imageUrl)
        assertEquals(expectedGuid, map.mapGuid)
    }
}