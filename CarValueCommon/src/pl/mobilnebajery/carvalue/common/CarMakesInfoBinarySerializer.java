package pl.mobilnebajery.carvalue.common;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.util.List;

import pl.mobilnebajery.carvalue.common.serializable.CarMakeInfo;
import pl.mobilnebajery.carvalue.common.serializable.CarMakesInfoList;
import pl.mobilnebajery.carvalue.common.serializable.CarModelInfo;
import pl.mobilnebajery.carvalue.common.serializable.CarPrice;

public class CarMakesInfoBinarySerializer implements ICarMakesInfoSerializer {

	public void serialize(List<CarMakeInfo> carInfos, String fileName) throws Exception {
	
		FileOutputStream flotpt = new FileOutputStream(fileName);

		// creation a "Flow object " with the flow file
		ObjectOutputStream objstr= new ObjectOutputStream(flotpt);
		
		CarMakesInfoList list = new CarMakesInfoList(carInfos);
		
		try {
			// Serialization : writing of subject in the flow of Release
			objstr.writeObject(list); 
			// we empty the buffer
			objstr.flush();

		} finally {
			//close of flow
			try {
				objstr.close();
			} finally {
				flotpt.close();
			}
		}
	}

	public List<CarMakeInfo> deserialize(InputStream inputStream) throws Exception {
		
		// creation a "Flow object " with the flow file
		ObjectInputStream objinstr= new PackageObjectInputStream(inputStream);
		
		List<CarMakeInfo> list = null;
		
		try {	
			// deserialization : reading of subject since the flow Input
			list = ((CarMakesInfoList)objinstr.readObject()).list; 
		} finally {
			// we farm the flow
			try {
				objinstr.close();
			} finally {
				//nothing
			}
		}
		
		return list;
	}
	
	public class PackageObjectInputStream extends ObjectInputStream {

		public PackageObjectInputStream(InputStream in) throws IOException {
			super(in);
		}

		@Override
		protected ObjectStreamClass readClassDescriptor() throws IOException,
				ClassNotFoundException {
			ObjectStreamClass desc = super.readClassDescriptor();
		    if (desc.getName().equals("xxx.ltech.carvalue.common.CarMakeInfo")) {
		        return ObjectStreamClass.lookup(CarMakeInfo.class);
		    }
		    else if (desc.getName().equals("xxx.ltech.carvalue.common.CarMakesInfoList")) {
		        return ObjectStreamClass.lookup(CarMakesInfoList.class);
		    }
		    else if (desc.getName().equals("xxx.ltech.carvalue.common.CarModelInfo")) {
		        return ObjectStreamClass.lookup(CarModelInfo.class);
		    }
		    else if (desc.getName().equals("xxx.ltech.carvalue.common.CarPrice")) {
		        return ObjectStreamClass.lookup(CarPrice.class);
		    }
		    return desc;
		}	
	}
}
