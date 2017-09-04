package org.jetbrains.fortran.lang.core.stubs

import com.intellij.psi.stubs.*
import org.jetbrains.fortran.lang.core.stubs.index.FortranNamedElementIndex
import org.jetbrains.fortran.lang.psi.FortranInterfaceBlock
import org.jetbrains.fortran.lang.psi.impl.FortranInterfaceBlockImpl

class FortranInterfaceBlockStub(
        parent: StubElement<*>?, elementType: IStubElementType<*, *>,
        override val name: String?
): StubBase<FortranInterfaceBlock>(parent, elementType), FortranNamedStub {
    object Type : FortranStubElementType<FortranInterfaceBlockStub, FortranInterfaceBlock>("Interface block") {

        override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): FortranInterfaceBlockStub =
                FortranInterfaceBlockStub(parentStub, this, dataStream.readName()?.string)

        override fun serialize(stub: FortranInterfaceBlockStub, dataStream: StubOutputStream) {
            with(dataStream) {
                writeName(stub.name)
            }
        }

        override fun createPsi(stub: FortranInterfaceBlockStub) = FortranInterfaceBlockImpl(stub, this)

        override fun createStub(psi: FortranInterfaceBlock, parentStub: StubElement<*>?) =
                FortranInterfaceBlockStub(parentStub, this, psi.name)

        override fun indexStub(stub: FortranInterfaceBlockStub, sink: IndexSink) {
            stub.name?.let {
                sink.occurrence(FortranNamedElementIndex.KEY, it)
            }
        }
    }
}