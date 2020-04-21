package simulations.utils

import java.util.UUID

import scala.util.Random

class RestaurantIdGenerator {

  private val restaurantIds: List[UUID] = List(
    UUID.fromString("f941e16c-efc9-42d7-823c-d44094d548e2"),
    UUID.fromString("6287cbb4-c02d-41b1-9625-eada9ad7aa4f"),
    UUID.fromString("8d43d523-228e-4ea9-838a-5463ec0f940a"),
    UUID.fromString("f880a113-aab3-416d-a8a1-1a729481fe0f"),
    UUID.fromString("ad9ef9be-92b1-463d-b814-81630ef9e71e"),
    UUID.fromString("c3c102b3-a3ad-4555-8dee-f2725b88933d"),
    UUID.fromString("95d45dc8-6930-435e-a746-c1e096c7f663"),
    UUID.fromString("f5500f81-2a06-4e07-90a3-23485f6f4154"),
    UUID.fromString("e55c7136-aade-49fa-86d6-617992d32217"),
    UUID.fromString("c77ca283-6dde-4a44-a889-06d66113a419"),
    UUID.fromString("978cb703-606e-45d4-94fc-719560cdf2b4"),
    UUID.fromString("13cabe22-fee9-4885-a635-0927bcd06f84"),
    UUID.fromString("c1215b94-0d29-4a6e-808a-521654d0fa27"),
    UUID.fromString("49efd4ba-8db7-419b-bb32-743f5ce02232"),
    UUID.fromString("7f3424d0-f396-473e-8728-12e353974ae9"),
    UUID.fromString("7346aaed-4dc0-4afb-baa0-5c5233bf4541"),
    UUID.fromString("6e7d7e33-033f-4d77-af5c-5845f56c67db"),
    UUID.fromString("ebc614d7-6e4e-43ca-8f37-d0c61278c8c7"),
    UUID.fromString("fdf055f9-5301-4d64-a0ad-aab49e42af3e"),
    UUID.fromString("7c5af8ab-c4b3-49b9-8b24-773a120e5a43"),
    UUID.fromString("ab9e398c-53e3-40c2-a648-83c14d69e08c"),
    UUID.fromString("ff495c8c-7914-4405-bf42-6e2e2e8665ea"),
    UUID.fromString("1ed9e83f-6e85-42e0-a835-7bbd332ce33e"),
    UUID.fromString("3d514e07-f26b-4fb1-9846-492051ecca27"),
    UUID.fromString("796395e8-3e27-4016-b36f-934a1af0898a"),
    UUID.fromString("0afa2633-3719-4077-b3d8-4ad02ef60da7"),
    UUID.fromString("47199f35-eec7-4aa3-92dc-d48b69db82ca"),
    UUID.fromString("b74864fd-395e-40f8-abe3-6916adc8d4e4"),
    UUID.fromString("378ebfc1-4ea6-4b4b-9b81-70e6a86a07fa"),
    UUID.fromString("bc72e506-3376-4f90-8a9e-f7887bc224ae"),
    UUID.fromString("49060bce-5fe8-408a-9163-592a17cd0c0c"),
    UUID.fromString("8c442a9d-292e-4413-bc79-887b6687d709"),
    UUID.fromString("a663cbb4-13bc-488a-bdfd-def8068a5e90"),
    UUID.fromString("7cb1342b-d634-490c-a08c-a779f631bd76"),
    UUID.fromString("ccb55f57-0afa-4f19-84d7-5d1597d47cb9"),
    UUID.fromString("a409fe01-2ee8-415f-9a59-8c85531ad788"),
    UUID.fromString("dedf939a-64ff-4299-a474-5ce4ebd8e552"),
    UUID.fromString("bc94683d-80fe-423f-8949-dda7692ef1cb"),
    UUID.fromString("0c25b2df-a0fb-4fc0-82f8-02936106dee7"),
    UUID.fromString("a0922e58-af83-4b72-8b3c-08d755cc2f1b"),
    UUID.fromString("203db3d9-31dc-4df1-82e8-9d8bb12e9dfe"),
    UUID.fromString("288897ce-f310-49b3-b281-4e7bd87b8d3e"),
    UUID.fromString("a588566a-b08b-4a4e-b785-10d30626d40b"),
    UUID.fromString("452ed023-1995-4a15-b6e1-3696694b132b"),
    UUID.fromString("6a38ca1c-3ee8-4fb0-92c6-2557174558a8"),
    UUID.fromString("b493764e-125b-449c-ac64-7fcdb17e07f5"),
    UUID.fromString("8117859c-5fc3-4c65-bc67-0e622554991a"),
    UUID.fromString("92328355-a88c-42ca-80cf-1f09a95e970b"),
    UUID.fromString("40eb5a0b-b041-4e29-8eb4-e5637aa5c74e"),
    UUID.fromString("199a3e7a-ad58-468b-aa71-a56304fc15a8"),
    UUID.fromString("617b7a87-db71-467f-9d33-b882ffe0b4f8")
  )

  private val rnd = new Random()

  def getRandomRestaurantId(): UUID = {
    restaurantIds(rnd.nextInt(restaurantIds.length))
  }

}
